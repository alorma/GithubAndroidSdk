package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoContentsClient extends GithubRepoClient<List<Content>> {

	private String path = null;

	public GetRepoContentsClient(Context context, RepoInfo repoInfo) {
		super(context, repoInfo);
	}

	public GetRepoContentsClient(Context context, RepoInfo repoInfo, String path) {
		super(context, repoInfo);
		this.path = path;
	}

	@Override
	protected void executeService(RepoService repoService) {
		if (path == null) {
			if (getBranch() == null) {
				repoService.contents(getOwner(), getRepo(), this);
			} else {
				repoService.contentsByRef(getOwner(), getRepo(), getBranch(), this);
			}
		} else {
			if (getBranch() == null) {
				repoService.contents(getOwner(), getRepo(), path, this);
			} else {
				repoService.contentsByRef(getOwner(), getRepo(), path, getBranch(), this);
			}
		}
	}

	@Override
	protected List<Content> executeServiceSync(RepoService repoService) {
		if (path == null) {
			if (getBranch() == null) {
				return repoService.contents(getOwner(), getRepo());
			} else {
				return repoService.contentsByRef(getOwner(), getRepo(), getBranch());
			}
		} else {
			if (getBranch() == null) {
				return repoService.contents(getOwner(), getRepo(), path);
			} else {
				return repoService.contentsByRef(getOwner(), getRepo(), path, getBranch());
			}
		}
	}
}
