package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

public class UserReposFromOrganizationClient extends GithubReposClient {

    public UserReposFromOrganizationClient(Context context) {
        super(context, null);
    }

    public UserReposFromOrganizationClient(Context context, int page) {
        super(context, null, page);
    }

    @Override
    protected void executeUserFirstPage(String sort, ReposService usersService,
        ApiSubscriber apiSubscriber) {
        usersService.userReposListFromOrgs(sort, apiSubscriber);
    }

    @Override
    protected void executeFirstPageByUsername(String username, String sort,
        ReposService usersService, ApiSubscriber apiSubscriber) {

    }

    @Override
    protected void executeUserPaginated(int page, String sort, ReposService usersService,
        ApiSubscriber apiSubscriber) {
        usersService.userReposListFromOrgs(page, sort, apiSubscriber);
    }

    @Override
    protected void executePaginatedByUsername(String username, int page, String sort,
        ReposService usersService, ApiSubscriber apiSubscriber) {

    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.moondragon+json";
    }
}
