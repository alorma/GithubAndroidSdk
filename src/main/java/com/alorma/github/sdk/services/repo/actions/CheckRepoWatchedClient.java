package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;

import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Bernat on 07/08/2014.
 */
public class CheckRepoWatchedClient extends GithubClient<Response> {
    private String repo;
    private String owner;

    public CheckRepoWatchedClient(Context context, String owner, String repo) {
        super(context);
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(RepoActionsService.class).checkIfRepoIsWatched(owner, repo, this);
    }

    @Override
    protected Response executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(RepoActionsService.class).checkIfRepoIsWatched(owner, repo);
    }
}
