package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/08/2014.
 */
public abstract class GithubIssuesClient<K> extends GithubClient<K> {

	private int page;
	private IssueInfo issueInfo;

	public GithubIssuesClient(Context context, IssueInfo issueInfo, int page) {
		this(context, issueInfo);
		this.page = page;
	}

	public GithubIssuesClient(Context context, IssueInfo issueInfo) {
		super(context);
		this.issueInfo = issueInfo;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		IssuesService issuesService = restAdapter.create(IssuesService.class);
		if (page == 0) {
			executeFirstPage(issueInfo, issuesService);
		} else {
			executePaginated(issueInfo, page, issuesService);
		}
	}

	@Override
	protected K executeServiceSync(RestAdapter restAdapter) {
		IssuesService issuesService = restAdapter.create(IssuesService.class);
		if (page == 0) {
			return executeFirstPageSync(issueInfo, issuesService);
		} else {
			return executePaginatedSync(issueInfo, page, issuesService);
		}
	}

	protected abstract void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService);

	protected abstract void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService);

	protected abstract K executeFirstPageSync(IssueInfo issueInfo, IssuesService issuesService);

	protected abstract K executePaginatedSync(IssueInfo issueInfo, int page, IssuesService issuesService);

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.text+json";
	}
}
