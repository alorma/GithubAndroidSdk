package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 18/07/2015.
 */
public class MemberReposClient extends GithubClient<List<Repo>> {

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
    protected void executeService(RestAdapter restAdapter) {
        ReposService reposService = restAdapter.create(ReposService.class);
        if (page == 0) {
            reposService.userMemberRepos(this);
        } else {
            reposService.userMemberRepos(page, this);
        }
    }

    @Override
    protected List<Repo> executeServiceSync(RestAdapter restAdapter) {
        ReposService reposService = restAdapter.create(ReposService.class);
        if (page == 0) {
            return reposService.userMemberRepos();
        } else {
            return reposService.userMemberRepos(page);
        }
    }
}
