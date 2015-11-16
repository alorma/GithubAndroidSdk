package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubStatusResponse;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by a557114 on 06/09/2015.
 */
public class GetShaCombinedStatus extends GithubRepoClient<GithubStatusResponse> {

    private String ref;
    private int page;

    public GetShaCombinedStatus(Context context, RepoInfo repoInfo, String ref) {
        this(context, repoInfo, ref, 0);
    }

    public GetShaCombinedStatus(Context context, RepoInfo repoInfo, String ref, int page) {
        super(context, repoInfo);
        this.ref = ref;
        this.page = page;
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (page == 0) {
            repoService.combinedStatus(getOwner(), getRepo(), ref, this);
        } else {
            repoService.combinedStatus(getOwner(), getRepo(), ref, page, this);
        }
    }

    @Override
    protected GithubStatusResponse executeServiceSync(RepoService repoService) {
        if (page == 0) {
            return repoService.combinedStatus(getOwner(), getRepo(), ref);
        } else {
            return repoService.combinedStatus(getOwner(), getRepo(), ref, page);
        }
    }
}
