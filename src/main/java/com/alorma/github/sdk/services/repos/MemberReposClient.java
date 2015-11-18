package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 18/07/2015.
 */
public class MemberReposClient extends GithubListClient<List<Repo>> {

    private int page;

    public MemberReposClient(Context context) {
        super(context);
        page = 0;
    }

    public MemberReposClient(Context context, int page) {
        super(context);
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                ReposService reposService = restAdapter.create(ReposService.class);
                if (page == 0) {
                    reposService.userMemberRepos(this);
                } else {
                    reposService.userMemberRepos(page, this);
                }
            }
        };
    }
}
