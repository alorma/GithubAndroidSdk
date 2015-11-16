package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 10/05/2015.
 */
public class GithubIssueLabelsClient extends GithubClient<List<Label>> {
    private RepoInfo repoInfo;

    public GithubIssueLabelsClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.repoInfo = repoInfo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        IssuesService issueStoryService = restAdapter.create(IssuesService.class);
        new IssueLabelsCallback(repoInfo, issueStoryService).execute();
    }

    @Override
    protected List<Label> executeServiceSync(RestAdapter restAdapter) {
        IssuesService issueStoryService = restAdapter.create(IssuesService.class);
        List<Label> labels = new ArrayList<>();

        labels.addAll(issueStoryService.labels(repoInfo.owner, repoInfo.name, 1));

        for (int i = nextPage; i < lastPage; i++)
            labels.addAll(issueStoryService.labels(repoInfo.owner, repoInfo.name, i));
        return labels;
    }

    private class IssueLabelsCallback extends BaseInfiniteCallback<List<Label>> {

        private List<Label> labels;
        private RepoInfo repoInfo;
        private IssuesService service;

        public IssueLabelsCallback(RepoInfo repoInfo, IssuesService service) {
            this.repoInfo = repoInfo;
            this.service = service;
            labels = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.labels(repoInfo.owner, repoInfo.name, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.labels(repoInfo.owner, repoInfo.name, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(labels, null);
            }
        }

        @Override
        protected void response(List<Label> labels) {
            this.labels.addAll(labels);
        }
    }
}
