package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.repos.GithubReposClient;
import com.alorma.github.sdk.services.repos.ReposService;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

public class OrgsReposClient extends GithubReposClient {

	public OrgsReposClient(Context context, String org) {
		super(context, org);
	}

	public OrgsReposClient(Context context, String org, int page) {
		super(context, org, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {

	}

	@Override
	protected void executeFirstPageByUsername(String org, String sort, ReposService orgsService,
		ApiSubscriber apiSubscriber) {
		orgsService.orgsReposList(org, sort, apiSubscriber);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {

	}

	@Override
	protected void executePaginatedByUsername(String org, int page, String sort, ReposService orgsService,
		ApiSubscriber apiSubscriber) {
		orgsService.orgsReposList(org, page, sort, apiSubscriber);
	}
}
