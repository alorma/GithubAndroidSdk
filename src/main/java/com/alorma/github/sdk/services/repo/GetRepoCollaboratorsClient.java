package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.info.RepoInfo;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoCollaboratorsClient extends GithubRepoClient<List<User>> {

    private int page = 0;

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo, int page) {
        super(context, repoInfo);
        this.page = page;
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (page == 0) {
            repoService.collaborators(getOwner(), getRepo(), this);
        } else {
            repoService.collaborators(getOwner(), getRepo(), page, this);
        }
    }

    @Override
    protected List<User> executeServiceSync(RepoService repoService) {
        if (page == 0) {
            return repoService.collaborators(getOwner(), getRepo());
        } else {
            return repoService.collaborators(getOwner(), getRepo(), page);
        }
    }
}
