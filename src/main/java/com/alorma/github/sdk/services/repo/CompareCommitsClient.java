package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.CompareCommit;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 31/07/2015.
 */
public class CompareCommitsClient extends GithubClient<CompareCommit> {
    private final RepoInfo repoInfo;
    private final String base;
    private final String head;

    public CompareCommitsClient(Context context, RepoInfo repoInfo, String base, String head) {
        super(context);
        this.repoInfo = repoInfo;
        this.base = base;
        this.head = head;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(RepoService.class).compareCommits(repoInfo.owner, repoInfo.name, base, head, this);
    }

    @Override
    protected CompareCommit executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(RepoService.class).compareCommits(repoInfo.owner, repoInfo.name, base, head);
    }
}
