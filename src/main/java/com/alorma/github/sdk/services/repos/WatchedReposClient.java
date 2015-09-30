package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

/**
 * Created by Bernat on 17/07/2014.
 */
public class WatchedReposClient extends GithubReposClient {

	public WatchedReposClient(Context context, String username) {
		super(context, username);
	}

	public WatchedReposClient(Context context, String username, int page) {
		super(context, username, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService) {
		usersService.userSubscribedReposList(sort, this);
	}

	@Override
	protected void executeFirstPageByUsername(String username, String sort, ReposService usersService) {
		usersService.userSubscribedReposList(username, sort, this);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService) {
		usersService.userSubscribedReposList(page, sort, this);
	}

	@Override
	protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService) {
		usersService.userSubscribedReposList(username, page, sort, this);
	}

	@Override
	protected List<Repo> executeUserFirstPageSync(String sort, ReposService usersService) {
		return usersService.userSubscribedReposList(sort);
	}

	@Override
	protected List<Repo> executeFirstPageByUsernameSync(String username, String sort, ReposService usersService) {
		return usersService.userSubscribedReposList(username, sort);
	}

	@Override
	protected List<Repo> executeUserPaginatedSync(int page, String sort, ReposService usersService) {
		return usersService.userSubscribedReposList(page, sort);
	}

	@Override
	protected List<Repo> executePaginatedByUsernameSync(String username, int page, String sort, ReposService usersService) {
		return usersService.userSubscribedReposList(username, page, sort);
	}


}
