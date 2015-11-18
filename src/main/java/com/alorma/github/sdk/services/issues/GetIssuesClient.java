package com.alorma.github.sdk.services.issues;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/08/2014.
 */
public class GetIssuesClient extends GithubListClient<List<Issue>> {

	private final Map<String, String> filter;
	private IssueInfo issueInfo;
	private final int page;

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
		super(context);
		this.issueInfo = issueInfo;
		this.page = page;
		this.filter = filter != null ? filter : new HashMap<String, String>();
	}

	@Override
	protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
		return new ApiSubscriber() {
			@Override
			protected void call(RestAdapter restAdapter) {
				IssuesService issuesService = restAdapter.create(IssuesService.class);
				if (page == 0) {
					if (issueInfo != null) {
						issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter,
							this);
					} else {
						issuesService.issues(filter, this);
					}
				} else {
					if (issueInfo != null) {
						issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, filter,
							page, this);
					} else {
						issuesService.issues(filter, page, this);
					}
				}
			}
		};
	}
}
