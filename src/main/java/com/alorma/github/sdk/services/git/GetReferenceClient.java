package com.alorma.github.sdk.services.git;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GitReference;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class GetReferenceClient extends GithubClient<GitReference> {

    private final RepoInfo info;

    public GetReferenceClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.info = repoInfo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(GitDataService.class).repoReference(info.owner, info.name, info.branch, this);
    }

    @Override
    protected GitReference executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(GitDataService.class).repoReference(info.owner, info.name, info.branch);
    }
}
