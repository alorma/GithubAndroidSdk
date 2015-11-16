package com.alorma.github.sdk.services.git;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GitReference;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

public class GetReferencesClient extends GithubClient<List<GitReference>> {

    private final RepoInfo info;
    private final int page;

    public GetReferencesClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }

    public GetReferencesClient(Context context, RepoInfo repoInfo, int page) {
        super(context);
        this.info = repoInfo;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (page == 0) {
            restAdapter.create(GitDataService.class).repoReferences(info.owner, info.name, this);
        } else {
            restAdapter.create(GitDataService.class).repoReferences(info.owner, info.name, page, this);
        }
    }

    @Override
    protected List<GitReference> executeServiceSync(RestAdapter restAdapter) {
        if (page == 0) {
            return restAdapter.create(GitDataService.class).repoReferences(info.owner, info.name);
        } else {
            return restAdapter.create(GitDataService.class).repoReferences(info.owner, info.name, page);
        }
    }
}
