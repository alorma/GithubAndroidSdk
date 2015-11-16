package com.alorma.github.sdk.services.git;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GitReference;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;

public class GetReferencesClient extends GithubListClient<List<GitReference>> {

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
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                GitDataService gitDataService = restAdapter.create(GitDataService.class);
                if (page == 0) {
                    gitDataService
                        .repoReferences(info.owner, info.name, this);
                } else {
                    gitDataService
                        .repoReferences(info.owner, info.name, page, this);
                }
            }
        };
    }
}
