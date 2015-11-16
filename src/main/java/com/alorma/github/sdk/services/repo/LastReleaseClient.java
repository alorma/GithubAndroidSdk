package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 29/07/2015.
 */
public class LastReleaseClient extends GithubClient<Release> {
    private RepoInfo info;

    public LastReleaseClient(Context context, RepoInfo info) {
        super(context);
        this.info = info;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(RepoService.class).lastRelease(info.owner, info.name, this);
    }

    @Override
    protected Release executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(RepoService.class).lastRelease(info.owner, info.name);
    }
}
