package com.alorma.github.sdk.services.notifications;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Notification;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 18/02/2015.
 */
public class GetNotificationsClient extends GithubClient<List<Notification>> {

	private String token;

	public GetNotificationsClient(Context context) {
		super(context);
	}

	public GetNotificationsClient(Context context, String token) {
		super(context);
		this.token = token;
	}


	@Override
	public String getToken() {
		return token != null ? token : super.getToken();
	}

	@Override
	protected Observable<List<Notification>> getApiObservable(RestAdapter restAdapter) {
		return restAdapter.create(NotificationsService.class).getNotifications();
	}
}
