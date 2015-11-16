package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.issues.IssuesService;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class GetGistCommentsClient extends GithubClient<List<GithubComment>> {

    private String id;

    public GetGistCommentsClient(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        new GistCommentCallback(id, gistsService);
    }

    @Override
    protected List<GithubComment> executeServiceSync(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        List<GithubComment> comments = new ArrayList<>();

        comments.addAll(gistsService.comments(id, 1));

        for (int i = nextPage; i < lastPage; i++)
            comments.addAll(gistsService.comments(id, i));

        return comments;
    }

    private class GistCommentCallback extends BaseInfiniteCallback<List<GithubComment>>{

        private String id;
        private GistsService service;
        private List<GithubComment> comments;

        public GistCommentCallback(String id, GistsService gistsService) {
            this.id = id;
            this.service = gistsService;
            comments = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.comments(id, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.comments(id, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(comments, null);
            }
        }

        @Override
        protected void response(List<GithubComment> comments) {
            this.comments.addAll(comments);
        }
    }
}
