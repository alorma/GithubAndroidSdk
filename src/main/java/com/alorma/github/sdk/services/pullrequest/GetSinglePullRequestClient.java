package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class GetSinglePullRequestClient extends GithubClient<PullRequest> {

    private final IssueInfo info;

    public GetSinglePullRequestClient(Context context, IssueInfo info) {
        super(context);
        this.info = info;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(PullRequestsService.class).pull(info.repoInfo.owner, info.repoInfo.name, info.num, this);
    }

    @Override
    protected PullRequest executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(PullRequestsService.class).pull(info.repoInfo.owner, info.repoInfo.name, info.num);
    }
}
