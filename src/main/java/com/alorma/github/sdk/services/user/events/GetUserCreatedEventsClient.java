package com.alorma.github.sdk.services.user.events;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

public class GetUserCreatedEventsClient extends GithubClient<List<GithubEvent>>{

    private String username;
    private int page = 0;

    public GetUserCreatedEventsClient(Context context, String username) {
        super(context);
        this.username = username;
    }
    public GetUserCreatedEventsClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (page == 0) {
            restAdapter.create(EventsService.class).createdEvents(username, this);
        } else {
            restAdapter.create(EventsService.class).createdEvents(username, page, this);
        }
    }

    @Override
    protected List<GithubEvent> executeServiceSync(RestAdapter restAdapter) {
        if (page == 0) {
            return restAdapter.create(EventsService.class).createdEvents(username);
        } else {
            return restAdapter.create(EventsService.class).createdEvents(username, page);
        }
    }
}
