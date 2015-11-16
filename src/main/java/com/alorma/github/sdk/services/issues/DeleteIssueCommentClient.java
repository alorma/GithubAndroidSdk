package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;

public class DeleteIssueCommentClient extends GithubClient<Response>{

    private RepoInfo info;
    private String id;

    public DeleteIssueCommentClient(Context context, RepoInfo info, String id) {
        super(context);
        this.info = info;
        this.id = id;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(IssuesService.class).deleteComment(info.owner, info.name, id, this);
    }

    @Override
    protected Response executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(IssuesService.class).deleteComment(info.owner, info.name, id);
    }
}
