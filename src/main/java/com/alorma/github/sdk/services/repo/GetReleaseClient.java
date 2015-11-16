package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.info.ReleaseInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 29/07/2015.
 */
public class GetReleaseClient extends GithubClient<Release> {
    private ReleaseInfo info;

    public GetReleaseClient(Context context, ReleaseInfo info) {
        super(context);
        this.info = info;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(RepoService.class).release(info.repoInfo.owner, info.repoInfo.name, String.valueOf(info.num), this);
    }

    @Override
    protected Release executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(RepoService.class).release(info.repoInfo.owner, info.repoInfo.name, String.valueOf(info.num));
    }
}
