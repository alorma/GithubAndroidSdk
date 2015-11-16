package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by Bernat on 17/07/2014.
 */
public class GetRepoClient extends GithubRepoClient<Repo> {

	public GetRepoClient(Context context, RepoInfo repoInfo) {
		super(context, repoInfo);
	}

	@Override
    protected void executeService(RepoService repoService) {
        repoService.get(getOwner(), getRepo(), this);
    }

	@Override
	protected Repo executeServiceSync(RepoService repoService) {
		return  repoService.get(getOwner(), getRepo());
	}
}
