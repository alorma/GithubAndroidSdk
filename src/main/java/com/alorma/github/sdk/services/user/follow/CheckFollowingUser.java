package com.alorma.github.sdk.services.user.follow;

import android.content.Context;

import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.user.UsersService;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 27/12/2014.
 */
public class CheckFollowingUser extends GithubClient<Object> implements GithubClient.OnResultCallback<Object> {

	private OnCheckFollowingUser onCheckFollowingUser;
	private String username;

	public CheckFollowingUser(Context context, String username) {
		super(context);
		this.username = username;
		setOnResultCallback(this);
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(UsersService.class).checkFollowing(username, this);
	}

	@Override
	protected Object executeServiceSync(RestAdapter restAdapter) {
		return restAdapter.create(UsersService.class).checkFollowing(username);
	}

	@Override
	public void onResponseOk(Object o, Response r) {
		if (r != null && r.getStatus() == 204) {
			if (onCheckFollowingUser != null) {
				onCheckFollowingUser.onCheckFollowUser(username, true);
			}
		}
	}

	@Override
	public void onFail(RetrofitError error) {
		if (error != null && error.getResponse() != null && error.getResponse().getStatus() == 404) {
			if (onCheckFollowingUser != null) {
				onCheckFollowingUser.onCheckFollowUser(username, false);
			}
		}
	}

	public void setOnCheckFollowingUser(OnCheckFollowingUser onCheckFollowingUser) {
		this.onCheckFollowingUser = onCheckFollowingUser;
	}

}
