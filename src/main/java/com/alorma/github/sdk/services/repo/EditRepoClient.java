package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.RepoRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by a557114 on 01/08/2015.
 */
public class EditRepoClient extends GithubRepoClient<Repo> {

    private RepoRequestDTO repoRequestDTO;

    public EditRepoClient(Context context, RepoInfo repoInfo, RepoRequestDTO repoRequestDTO) {
        super(context, repoInfo);
        this.repoRequestDTO = repoRequestDTO;
    }

    @Override
    protected void executeService(RepoService repoService) {
        repoService.edit(getOwner(), getRepo(), repoRequestDTO, this);
    }

    @Override
    protected Repo executeServiceSync(RepoService repoService) {
        return repoService.edit(getOwner(), getRepo(), repoRequestDTO);
    }
}
