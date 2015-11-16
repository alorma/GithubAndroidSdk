package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.user.events.EventsService;

import java.util.List;

import retrofit.RestAdapter;

public class GetRepoEventsClient extends GithubClient<List<GithubEvent>> {

    private int page;
    private RepoInfo info;

    public GetRepoEventsClient(Context context, RepoInfo info) {
        super(context);
        this.info = info;
    }

    public GetRepoEventsClient(Context context, RepoInfo info, int page) {
        this(context, info);
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (page == 0) {
            restAdapter.create(RepoService.class).events(info.owner, info.name, this);
        } else {
            restAdapter.create(RepoService.class).events(info.owner, info.name, page, this);
        }
    }

    @Override
    protected List<GithubEvent> executeServiceSync(RestAdapter restAdapter) {
        if (page == 0) {
            return restAdapter.create(RepoService.class).events(info.owner, info.name);
        } else {
            return restAdapter.create(RepoService.class).events(info.owner, info.name, page);
        }
    }
}
