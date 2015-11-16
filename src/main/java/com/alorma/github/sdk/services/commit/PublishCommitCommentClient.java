package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.CommitCommentRequest;
import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.info.CommitInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class PublishCommitCommentClient extends GithubClient<CommitComment>{

    private CommitInfo info;
    private CommitCommentRequest request;

    public PublishCommitCommentClient(Context context, CommitInfo info, CommitCommentRequest request) {
        super(context);
        this.info = info;
        this.request = request;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(CommitsService.class).publishComment(info.repoInfo.owner, info.repoInfo.name, info.sha, request, this);
    }

    @Override
    protected CommitComment executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(CommitsService.class).publishComment(info.repoInfo.owner, info.repoInfo.name, info.sha, request);
    }
}
