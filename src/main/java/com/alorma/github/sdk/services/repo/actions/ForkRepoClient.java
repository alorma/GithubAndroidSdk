package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.Objects;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ForkRepoClient extends GithubClient<Repo> {

    private RepoInfo repoInfo;
    private String org;

    public ForkRepoClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.repoInfo = repoInfo;
    }

    public ForkRepoClient(Context context, RepoInfo repoInfo, String org) {
        this(context, repoInfo);
        this.org = org;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if(org == null)
            restAdapter.create(RepoActionsService.class).forkRepo(repoInfo.owner, repoInfo.name, new Object(), this);
        else
            restAdapter.create(RepoActionsService.class).forkRepo(repoInfo.owner, repoInfo.name, org, new Object(), this);
    }

    @Override
    protected Repo executeServiceSync(RestAdapter restAdapter) {
        if(org == null)
            return restAdapter.create(RepoActionsService.class).forkRepo(repoInfo.owner, repoInfo.name, new Object());
        else
            return restAdapter.create(RepoActionsService.class).forkRepo(repoInfo.owner, repoInfo.name, org, new Object());
    }
}
