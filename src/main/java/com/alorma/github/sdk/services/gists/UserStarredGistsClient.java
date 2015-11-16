package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 08/07/2014.
 */
public class UserStarredGistsClient extends GithubClient<List<Gist>> {

	private String username;
	private int page = 0;

	public UserStarredGistsClient(Context context) {
		super(context);
	}

	public UserStarredGistsClient(Context context, int page) {
		super(context);
		this.page = page;
	}

	public UserStarredGistsClient(Context context, String username) {
		super(context);
		this.username = username;
	}

	public UserStarredGistsClient(Context context, String username, int page) {
		super(context);
		this.username = username;
		this.page = page;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		GistsService gistsService = restAdapter.create(GistsService.class);
		if (page == 0) {
			gistsService.userStarredGistsList(this);
		} else {
			gistsService.userStarredGistsList(page, this);
		}
	}

	@Override
	protected List<Gist> executeServiceSync(RestAdapter restAdapter) {
		GistsService gistsService = restAdapter.create(GistsService.class);
		if (page == 0) {
			return gistsService.userStarredGistsList();
		} else {
			return gistsService.userStarredGistsList(page);
		}
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.raw";
	}
}
