package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.R;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.github.sdk.utils.GitskariosSettings;

import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public abstract class GithubReposClient extends GithubListClient<List<Repo>> {
    private String username;
    private int page;

    public GithubReposClient(Context context) {
        this(context, null);
    }

    public GithubReposClient(Context context, String username) {
        this(context, username, 0);
    }
   
    public GithubReposClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                ReposService reposService = restAdapter.create(ReposService.class);
                GitskariosSettings settings = new GitskariosSettings(context);
                String sort = settings.getRepoSort(context.getString(R.string.sort_pushed_value));
                if (page == 0) {
                    if (username == null) {
                        executeUserFirstPage(sort, reposService, this);
                    } else {
                        executeFirstPageByUsername(username, sort, reposService, this);
                    }
                } else {
                    if (username == null) {
                        executeUserPaginated(page, sort, reposService, this);
                    } else {
                        executePaginatedByUsername(username, page, sort, reposService, this);
                    }
                }
            }
        };
    }

    protected abstract void executeUserFirstPage(String sort, ReposService usersService,
        ApiSubscriber apiSubscriber);
    protected abstract void executeFirstPageByUsername(String username, String sort, ReposService usersService,
        ApiSubscriber apiSubscriber);
    protected abstract void executeUserPaginated(int page, String sort, ReposService usersService,
        ApiSubscriber apiSubscriber);
    protected abstract void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
        ApiSubscriber apiSubscriber);
}
