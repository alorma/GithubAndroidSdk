package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.CommitInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/12/2014.
 */
public class GetSingleCommitClient extends GithubClient<Commit> {

	private CommitInfo info;

	public GetSingleCommitClient(Context context, CommitInfo info) {
		super(context);
		this.info = info;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(CommitsService.class).singleCommit(info.repoInfo.owner, info.repoInfo.name, info.sha, this);
	}

	@Override
	protected Commit executeServiceSync(RestAdapter restAdapter) {
		return restAdapter.create(CommitsService.class).singleCommit(info.repoInfo.owner, info.repoInfo.name, info.sha);
	}
}
