package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.info.CommitInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Bernat on 22/12/2014.
 */
public class GetCommitCommentsClient extends GithubListClient<List<CommitComment>> {

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
	public String getAcceptHeader() {
		return "application/vnd.github.v3.html+json";
	}

	@Override
	protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
		return new ApiSubscriber() {
			@Override
			protected void call(RestAdapter restAdapter) {
				CommitsService commitsService = restAdapter.create(CommitsService.class);
				if (page == 0) {
					commitsService
						.singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha,
							this);
				} else {
					commitsService
						.singleCommitComments(info.repoInfo.owner, info.repoInfo.name, info.sha,
							page, this);
				}
			}
		};
	}
}
