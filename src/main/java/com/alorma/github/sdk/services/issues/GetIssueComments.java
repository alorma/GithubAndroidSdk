package com.alorma.github.sdk.services.issues;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 23/08/2014.
 */
public class GetIssueComments extends GithubListClient<List<GithubComment>> {

	private IssueInfo issueInfo;
	private int page;

	public GetIssueComments(Context context, IssueInfo issueInfo) {
		this(context, issueInfo, 0);
	}

	public GetIssueComments(Context context, IssueInfo issueInfo, int page) {
		super(context);
		this.issueInfo = issueInfo;
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
				IssuesService issuesService = restAdapter.create(IssuesService.class);
				if (page == 0) {
					issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
						this);
				} else {
					issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
						page, this);
				}
			}
		};
	}
}
