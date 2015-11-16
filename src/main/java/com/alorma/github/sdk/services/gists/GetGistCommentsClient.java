package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.github.sdk.services.issues.IssuesService;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class GetGistCommentsClient extends GithubListClient<List<GithubComment>> {

    private String id;
    private int page;

    public GetGistCommentsClient(Context context, String id) {
        this(context, id, 0);
    }

    public GetGistCommentsClient(Context context, String id, int page) {
        super(context);
        this.id = id;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                GistsService gistsService = restAdapter.create(GistsService.class);

                if (page == 0) {
                    gistsService.comments(id, this);
                } else {
                    gistsService.comments(id, page, this);
                }
            }
        };
    }
}
