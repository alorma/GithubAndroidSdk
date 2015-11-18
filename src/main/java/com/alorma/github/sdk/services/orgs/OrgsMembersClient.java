package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.github.sdk.services.user.UsersService;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/02/2015.
 */
public class OrgsMembersClient extends GithubListClient<List<User>> {

	private final String org;
	private int page = 0;

	public OrgsMembersClient(Context context, String org) {
		super(context);
		this.org = org;
	}

	public OrgsMembersClient(Context context, String org, int page) {
		super(context);
		this.org = org;
		this.page = page;
	}

	@Override
	protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
		return new ApiSubscriber() {
			@Override
			protected void call(RestAdapter restAdapter) {
				UsersService usersService = restAdapter.create(UsersService.class);
				if (page == 0) {
					usersService.orgMembers(org, this);
				} else {
					usersService.orgMembers(org, page, this);
				}
			}
		};
	}
}
