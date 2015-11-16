package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.info.RepoInfo;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoBranchesClient extends GithubRepoClient<List<Branch>> {

	public GetRepoBranchesClient(Context context, RepoInfo repoInfo) {
		super(context, repoInfo);
	}

	@Override
	protected void executeService(RepoService repoService) {
		repoService.branches(getOwner(), getRepo(), this);
	}

	@Override
	protected List<Branch> executeServiceSync(RepoService repoService) {
		return repoService.branches(getOwner(), getRepo());
	}
}
