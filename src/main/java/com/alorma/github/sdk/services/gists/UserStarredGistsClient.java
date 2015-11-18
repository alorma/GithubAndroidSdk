package com.alorma.github.sdk.services.gists;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 08/07/2014.
 */
public class UserStarredGistsClient extends GithubListClient<List<Gist>> {

	private int page = 0;

	public UserStarredGistsClient(Context context) {
		super(context);
	}

	public UserStarredGistsClient(Context context, int page) {
		super(context);
		this.page = page;
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.raw";
	}

	@Override
	protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
		return new ApiSubscriber() {
			@Override
			protected void call(RestAdapter restAdapter) {
				GistsService gistsService = restAdapter.create(GistsService.class);
				if (page == 0) {
					gistsService.userStarredGistsList(this);
				} else {
					gistsService.userStarredGistsList(page, this);
				}
			}
		};
	}
}
