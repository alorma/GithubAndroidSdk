package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/08/2014.
 */
public class GetPullsClient extends GithubListClient<List<PullRequest>> {

    private final IssueInfo issueInfo;
    private int page = 0;

    public GetPullsClient(Context context, IssueInfo issueInfo) {
        super(context);
        this.issueInfo = issueInfo;
    }

    public GetPullsClient(Context context, IssueInfo issueInfo, int page) {
        super(context);
        this.issueInfo = issueInfo;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                PullRequestsService service = restAdapter.create(PullRequestsService.class);
                if (page == 0) {
                    service.pulls(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state), this);
                } else {
                    service.pulls(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state), page, this);
                }
            }
        };
    }
}
