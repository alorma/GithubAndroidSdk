package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

public class UserGistsClient extends GithubListClient<List<Gist>> {

    private String username;
    private int page = 0;

    public UserGistsClient(Context context) {
        super(context);
    }
    public UserGistsClient(Context context, int page) {
        super(context);
		this.page = page;
    }
    
	public UserGistsClient(Context context, String username) {
        super(context);
        this.username = username;
    }

    public UserGistsClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }


    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                GistsService gistsService = restAdapter.create(GistsService.class);
                if (page == 0) {
                    if (username == null) {
                        gistsService.userGistsListAsync(this);
                    } else {
                        gistsService.userGistsListAsync(username, this);
                    }
                } else {
                    if (username == null) {
                        gistsService.userGistsListAsync(page, this);
                    } else {
                        gistsService.userGistsListAsync(username, page, this);
                    }
                }
            }
        };
    }

    @Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.raw";
	}

}
