package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class GetAssigneesClient extends GithubClient<List<User>> {

    private RepoInfo repoInfo;

    public GetAssigneesClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.repoInfo = repoInfo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        IssuesService service = restAdapter.create(IssuesService.class);
        new AssigneesCallback(repoInfo, service).execute();
    }

    @Override
    protected List<User> executeServiceSync(RestAdapter restAdapter) {
        IssuesService issuesService = restAdapter.create(IssuesService.class);
        List<User> assignees = new ArrayList<>();

        assignees.addAll(issuesService.assignees(repoInfo.owner, repoInfo.name, 1));

        for (int i = nextPage; i < lastPage; i++)
            assignees.addAll(issuesService.assignees(repoInfo.owner, repoInfo.name, i));
        return assignees;
    }

    private class AssigneesCallback extends BaseInfiniteCallback<List<User>> {

        private List<User> assignees;
        private RepoInfo repoInfo;
        private IssuesService service;

        public AssigneesCallback(RepoInfo repoInfo, IssuesService service) {
            this.repoInfo = repoInfo;
            this.service = service;
            assignees = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.assignees(repoInfo.owner, repoInfo.name, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.assignees(repoInfo.owner, repoInfo.name, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(assignees, null);
            }
        }

        @Override
        protected void response(List<User> assignees) {
            this.assignees.addAll(assignees);
        }
    }
}
