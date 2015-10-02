package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 29/07/2015.
 */
public class GetRepoReleasesClient extends GithubClient<List<Release>> {
    private RepoInfo info;
    private int page;

    public GetRepoReleasesClient(Context context, RepoInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (page == 0) {
            restAdapter.create(RepoService.class).releases(info.owner, info.name, this);
        } else {
            restAdapter.create(RepoService.class).releases(info.owner, info.name, page, this);
        }
    }

    @Override
    protected List<Release> executeServiceSync(RestAdapter restAdapter) {
        if (page == 0) {
            return restAdapter.create(RepoService.class).releases(info.owner, info.name);
        } else {
            return restAdapter.create(RepoService.class).releases(info.owner, info.name, page);
        }
    }
}
