package com.alorma.github.sdk.services.orgs;

import android.content.Context;
import android.util.Pair;
import com.alorma.github.sdk.bean.dto.response.Organization;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import com.alorma.gitskarios.core.client.StoreCredentials;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 04/09/2014.
 */
public class GetOrgsClient extends GithubListClient<List<Organization>> {
	private String username;
	private int page = -1;

	public GetOrgsClient(Context context, String username) {
		super(context);
		this.username = username;
	}

	public GetOrgsClient(Context context, String org, int page) {
		super(context);
		this.username = org;
		this.page = page;
	}

	@Override
	protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
		return new ApiSubscriber() {
			@Override
			protected void call(RestAdapter restAdapter) {
				StoreCredentials settings = new StoreCredentials(getContext());
				if (username != null && username.equalsIgnoreCase(settings.getUserName())) {
					username = null;
				}
				OrgsService orgsService = restAdapter.create(OrgsService.class);
				if (page == -1) {
					if (username == null) {
						orgsService.orgs(this);
					} else {
						orgsService.orgsByUser(username, this);
					}
				} else {
					if (username == null) {
						orgsService.orgs(page, this);
					} else {
						orgsService.orgsByUser(username, page, this);
					}
				}
			}
		};
	}
}
