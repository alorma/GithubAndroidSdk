package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.info.CommitInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Bernat on 22/12/2014.
 */
public class GetCommitCommentsClient extends GithubClient<List<CommitComment>> {

	private CommitInfo info;
	private int page = 0;

	public GetCommitCommentsClient(Context context, CommitInfo info) {
		super(context);
		this.info = info;
	}
	public GetCommitCommentsClient(Context context, CommitInfo info, int page) {
		super(context);
		this.info = info;
		this.page = page;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		if (page == 0) {
			restAdapter.create(CommitsService.class).singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha, this);
		} else {
			restAdapter.create(CommitsService.class).singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha, page, this);
		}
	}

	@Override
	protected List<CommitComment> executeServiceSync(RestAdapter restAdapter) {
		if (page == 0) {
			return restAdapter.create(CommitsService.class).singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha);
		} else {
			return restAdapter.create(CommitsService.class).singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha, page);
		}
	}


	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.html+json";
	}
}
