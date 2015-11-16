package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.info.RepoInfo;

import retrofit.client.Response;

/**
 * Created by a557114 on 01/08/2015.
 */
public class DeleteRepoClient extends GithubRepoClient<Response> {

    public DeleteRepoClient(Context context, RepoInfo repoInfo) {
        super(context, repoInfo);
    }

    @Override
    protected void executeService(RepoService repoService) {
        repoService.delete(getOwner(), getRepo(), this);
    }

    @Override
    protected Response executeServiceSync(RepoService repoService) {
        return repoService.delete(getOwner(), getRepo());
    }
}
