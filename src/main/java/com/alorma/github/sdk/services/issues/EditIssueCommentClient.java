package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.CommentRequest;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class EditIssueCommentClient extends GithubClient<GithubComment>{

    private RepoInfo info;
    private String id;
    private CommentRequest body;

    public EditIssueCommentClient(Context context, RepoInfo info, String id, CommentRequest body) {
        super(context);
        this.info = info;
        this.id = id;
        this.body = body;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(IssuesService.class).editComment(info.owner, info.name, id, body, this);
    }

    @Override
    protected GithubComment executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(IssuesService.class).editComment(info.owner, info.name, id, body);
    }
}
