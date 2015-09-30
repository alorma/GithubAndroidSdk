package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;

import com.alorma.gitskarios.core.client.BaseClient;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 07/08/2014.
 */
public class CheckRepoStarredClient extends GithubClient<Response> implements BaseClient.OnResultCallback<Response> {
    private String repo;
    private String owner;
    private OnCheckStarredRepo onCheckStarredRepo;

    public CheckRepoStarredClient(Context context, String owner, String repo) {
        super(context);
        this.owner = owner;
        this.repo = repo;
        setOnResultCallback(this);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(RepoActionsService.class).checkIfRepoIsStarred(owner, repo, this);
    }

    @Override
    protected Response executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(RepoActionsService.class).checkIfRepoIsStarred(owner, repo);
    }

    @Override
    public void onResponseOk(Response o, Response r) {
        if (r != null && r.getStatus() == 204) {
            if (onCheckStarredRepo != null) {
                onCheckStarredRepo.onCheckStarredRepo(repo, true);
            }
        }
    }

    @Override
    public void onFail(RetrofitError error) {
        if (error != null && error.getResponse() != null && error.getResponse().getStatus() == 404) {
            if (onCheckStarredRepo != null) {
                onCheckStarredRepo.onCheckStarredRepo(repo, false);
            }
        }
    }

    public void setOnCheckStarredRepo(OnCheckStarredRepo onCheckStarredRepo) {
        this.onCheckStarredRepo = onCheckStarredRepo;
    }
}
