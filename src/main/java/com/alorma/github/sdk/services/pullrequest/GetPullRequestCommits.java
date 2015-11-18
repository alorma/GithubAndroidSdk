package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 17/06/2015.
 */
public class GetPullRequestCommits extends GithubListClient<List<Commit>> {

    private IssueInfo info;
    private int page;

    public GetPullRequestCommits(Context context, IssueInfo info) {
        this(context, info, 0);
    }


    public GetPullRequestCommits(Context context, IssueInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                PullRequestsService pullRequestsService = restAdapter.create(PullRequestsService.class);
                if (page == 0) {
                    pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, this);
                } else {
                    pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num,
                        nextPage, this);
                }
            }
        };
    }
}
