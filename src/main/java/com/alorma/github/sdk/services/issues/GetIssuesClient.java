package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bernat on 22/08/2014.
 */
public class GetIssuesClient extends GithubIssuesClient<List<Issue>> {

	private final Map<String, String> filter;

	public GetIssuesClient(Context context, Map<String, String> filter) {
		this(context, null, filter, 0);
	}

	public GetIssuesClient(Context context, Map<String, String> filter, int page) {
		this(context, null, filter, page);
	}

	public GetIssuesClient(Context context, IssueInfo issueInfo, Map<String, String> filter) {
		this(context, issueInfo, filter, 0);
	}

	public GetIssuesClient(Context context, IssueInfo issueInfo, Map<String, String> filter, int page) {
		super(context, issueInfo, page);
		this.filter = filter != null ? filter : new HashMap<String, String>();
	}

	@Override
	protected void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService) {
		if(issueInfo != null)
			issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter, this);
		else
			issuesService.issues(filter, this);
	}

	@Override
	protected void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService) {
		if(issueInfo != null)
			issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter, page, this);
		else
			issuesService.issues(filter, page, this);
	}

	@Override
	protected List<Issue> executeFirstPageSync(IssueInfo issueInfo, IssuesService issuesService) {
		if(issueInfo != null)
			return issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter);
		else
			return issuesService.issues(filter);
	}

	@Override
	protected List<Issue> executePaginatedSync(IssueInfo issueInfo, int page, IssuesService issuesService) {
		if(issueInfo != null)
			return issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter, page);
		else
			return issuesService.issues(filter, page);
	}
}
