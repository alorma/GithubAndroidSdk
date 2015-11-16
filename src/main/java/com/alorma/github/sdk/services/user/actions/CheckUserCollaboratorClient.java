package com.alorma.github.sdk.services.user.actions;

import android.content.Context;

import com.alorma.gitskarios.core.client.BaseClient;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 07/08/2014.
 */
public class CheckUserCollaboratorClient extends GithubClient<Object> implements BaseClient.OnResultCallback<Object> {
    private RepoInfo info;
    private String user;
    private OnCheckUserIsCollaborator onCheckUserIsCollaborator;

    public CheckUserCollaboratorClient(Context context, RepoInfo info, String user) {
        super(context);
        this.info = info;
        this.user = user;
        setOnResultCallback(this);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(UserActionsService.class).checkIfUserIsCollaborator(info.owner, info.name, user, this);
    }

    @Override
    protected Object executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(UserActionsService.class).checkIfUserIsCollaborator(info.owner, info.name, user);
    }

    @Override
    public void onResponseOk(Object o, Response r) {
        if (r != null && r.getStatus() == 204) {
            if (onCheckUserIsCollaborator != null) {
                onCheckUserIsCollaborator.onCheckUserIsCollaborator(user, true);
            }
        }
    }

    @Override
    public void onFail(RetrofitError error) {
        if (error != null && error.getResponse() != null && error.getResponse().getStatus() == 404) {
            if (onCheckUserIsCollaborator != null) {
                onCheckUserIsCollaborator.onCheckUserIsCollaborator(user, false);
            }
        }
    }

    public void setOnCheckUserIsCollaborator(OnCheckUserIsCollaborator onCheckUserIsCollaborator) {
        this.onCheckUserIsCollaborator = onCheckUserIsCollaborator;
    }
}
