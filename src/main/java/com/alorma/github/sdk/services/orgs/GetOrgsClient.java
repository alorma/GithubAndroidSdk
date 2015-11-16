package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.gitskarios.core.client.StoreCredentials;
import com.alorma.github.sdk.bean.dto.response.Organization;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 04/09/2014.
 */
public class GetOrgsClient extends GithubClient<List<Organization>> {
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
	protected void executeService(RestAdapter restAdapter) {
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

	@Override
	protected List<Organization> executeServiceSync(RestAdapter restAdapter) {
		StoreCredentials settings = new StoreCredentials(getContext());
		if (username != null && username.equalsIgnoreCase(settings.getUserName())) {
			username = null;
		}
		OrgsService orgsService = restAdapter.create(OrgsService.class);
		if (page == -1) {
			if (username == null) {
				return orgsService.orgs();
			} else {
				return orgsService.orgsByUser(username);
			}
		} else {
			if (username == null) {
				return orgsService.orgs(page);
			} else {
				return orgsService.orgsByUser(username, page);
			}
		}
	}
}
