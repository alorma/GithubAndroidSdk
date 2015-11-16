package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 08/07/2014.
 */
public class UserGistsClient extends GithubClient<List<Gist>> {

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
    protected void executeService(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        if (page == 0) {
            if (username == null) {
                gistsService.userGistsList(this);
            } else {
                gistsService.userGistsList(username, this);
            }
        } else {
            if (username == null) {
                gistsService.userGistsList(page, this);
            } else {
                gistsService.userGistsList(username, page, this);
            }
        }
    }

    @Override
    protected List<Gist> executeServiceSync(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        if (page == 0) {
            if (username == null) {
                return gistsService.userGistsList();
            } else {
                return gistsService.userGistsList(username);
            }
        } else {
            if (username == null) {
                return gistsService.userGistsList(page);
            } else {
                return gistsService.userGistsList(username, page);
            }
        }
    }

    @Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.raw";
	}
}
