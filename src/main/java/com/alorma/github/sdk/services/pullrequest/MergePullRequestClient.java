package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.MergeButtonRequest;
import com.alorma.github.sdk.bean.dto.response.MergeButtonResponse;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 21/06/2015.
 */
public class MergePullRequestClient extends GithubClient<MergeButtonResponse> {
    private IssueInfo issueInfo;
    private MergeButtonRequest mergeButtonRequest;

    public MergePullRequestClient(Context context, IssueInfo issueInfo, MergeButtonRequest mergeButtonRequest) {
        super(context);
        this.issueInfo = issueInfo;
        this.mergeButtonRequest = mergeButtonRequest;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (issueInfo != null && mergeButtonRequest != null) {
            restAdapter.create(PullRequestsService.class).merge(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, mergeButtonRequest, this);
        }
    }

    @Override
    protected MergeButtonResponse executeServiceSync(RestAdapter restAdapter) {
        if (issueInfo != null && mergeButtonRequest != null) {
            return restAdapter.create(PullRequestsService.class).merge(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, mergeButtonRequest);
        }
        return null;
    }
}
