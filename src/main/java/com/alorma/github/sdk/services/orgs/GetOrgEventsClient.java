package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

public class GetOrgEventsClient extends GithubClient<List<GithubEvent>> {

    private String username;
    private String org;
    private int page;

    public GetOrgEventsClient(Context context, String username, String org) {
        super(context);
        this.username = username;
        this.org = org;
    }

    public GetOrgEventsClient(Context context, String username, String org, int page) {
        this(context, username, org);
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if(page == 0)
            restAdapter.create(OrgsService.class).events(username, org, this);
        else
            restAdapter.create(OrgsService.class).events(username, org, page, this);
    }

    @Override
    protected List<GithubEvent> executeServiceSync(RestAdapter restAdapter) {
        if(page == 0)
            return restAdapter.create(OrgsService.class).events(username, org);
        else
            return restAdapter.create(OrgsService.class).events(username, org, page);
    }
}
