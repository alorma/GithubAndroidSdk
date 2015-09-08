package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

/**
 * Created by Bernat on 17/07/2014.
 */
public class StarredReposClient extends GithubReposClient {

	public StarredReposClient(Context context, String username) {
		super(context, username);
	}

	public StarredReposClient(Context context, String username, int page) {
		super(context, username, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService) {
		usersService.userStarredReposList(sort, this);
	}

	@Override
	protected void executeFirstPageByUsername(String username, String sort, ReposService usersService) {
		usersService.userStarredReposList(username, sort, this);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService) {
		usersService.userStarredReposList(page, sort, this);
	}

	@Override
	protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService) {
		usersService.userStarredReposList(username, page, sort, this);
	}

	@Override
	protected List<Repo> executeUserFirstPageSync(String sort, ReposService usersService) {
		return usersService.userStarredReposList(sort);
	}

	@Override
	protected List<Repo> executeFirstPageByUsernameSync(String username, String sort, ReposService usersService) {
		return usersService.userStarredReposList(username, sort);
	}

	@Override
	protected List<Repo> executeUserPaginatedSync(int page, String sort, ReposService usersService) {
		return usersService.userStarredReposList(page, sort);
	}

	@Override
	protected List<Repo> executePaginatedByUsernameSync(String username, int page, String sort, ReposService usersService) {
		return usersService.userStarredReposList(username, page, sort);
	}


}
