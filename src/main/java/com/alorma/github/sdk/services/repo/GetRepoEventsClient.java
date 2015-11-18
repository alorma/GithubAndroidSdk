package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.github.sdk.services.user.events.EventsService;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

public class GetRepoEventsClient extends GithubListClient<List<GithubEvent>> {

    private int page;
    private RepoInfo info;

    public GetRepoEventsClient(Context context, RepoInfo info) {
        this(context, info, 0);
    }

    public GetRepoEventsClient(Context context, RepoInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                RepoService repoService = restAdapter.create(RepoService.class);
                if (page == 0) {
                    repoService.events(info.owner, info.name, this);
                } else {
                    repoService.events(info.owner, info.name, page, this);
                }
            }
        };
    }
}
