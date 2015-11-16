package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

public class UserReposClient extends GithubReposClient {

	public UserReposClient(Context context) {
		super(context);
	}

	public UserReposClient(Context context, String username) {
		super(context, username);
	}

	public UserReposClient(Context context, String username, int page) {
		super(context, username, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userReposList(sort, apiSubscriber);
	}

	@Override
	protected void executeFirstPageByUsername(String username, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userReposList(username, sort, apiSubscriber);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userReposList(page, sort, apiSubscriber);
	}

	@Override
	protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
		ApiSubscriber apiSubscriber) {
		usersService.userReposList(username, page, sort, apiSubscriber);
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.moondragon+json";
	}
}
