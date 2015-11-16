package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

/**
 * Created by Bernat on 17/07/2014.
 */
public class StarredReposClient extends GithubReposClient {

	public StarredReposClient(Context context) {
		super(context);
	}

	public StarredReposClient(Context context, String username) {
		super(context, username);
	}

	public StarredReposClient(Context context, String username, int page) {
		super(context, username, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userStarredReposList(sort, apiSubscriber);
	}

	@Override
	protected void executeFirstPageByUsername(String username, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userStarredReposList(username, sort, apiSubscriber);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userStarredReposList(page, sort, apiSubscriber);
	}

	@Override
	protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userStarredReposList(username, page, sort, apiSubscriber);
	}

}
