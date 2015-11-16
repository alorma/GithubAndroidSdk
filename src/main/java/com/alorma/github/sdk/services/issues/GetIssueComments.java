package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.info.IssueInfo;

import java.util.List;

/**
 * Created by Bernat on 23/08/2014.
 */
public class GetIssueComments extends GithubIssuesClient<List<GithubComment>> {

	public GetIssueComments(Context context, IssueInfo issueInfo, int page) {
		super(context, issueInfo, page);
	}

	public GetIssueComments(Context context, IssueInfo issueInfo) {
		super(context, issueInfo);
	}

	@Override
	protected void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService) {
		issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, this);
	}

	@Override
	protected void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService) {
		issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, page, this);
	}

	@Override
	protected List<GithubComment> executeFirstPageSync(IssueInfo issueInfo, IssuesService issuesService) {
		return issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num);
	}

	@Override
	protected List<GithubComment> executePaginatedSync(IssueInfo issueInfo, int page, IssuesService issuesService) {
		return issuesService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, page);
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.html+json";
	}
}
