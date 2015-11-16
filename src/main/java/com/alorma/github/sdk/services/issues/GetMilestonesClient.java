package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.MilestoneState;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * Created by Bernat on 14/04/2015.
 */
public class GetMilestonesClient extends GithubClient<List<Milestone>> {

    private RepoInfo repoInfo;
    private MilestoneState state;

    public GetMilestonesClient(Context context, RepoInfo repoInfo, MilestoneState state) {
        super(context);
        this.repoInfo = repoInfo;
        this.state = state;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        IssuesService issuesService = restAdapter.create(IssuesService.class);
        new IssueMilestonesCallback(repoInfo, issuesService).execute();
    }

    @Override
    protected List<Milestone> executeServiceSync(RestAdapter restAdapter) {
        IssuesService issuesService = restAdapter.create(IssuesService.class);
        List<Milestone> milestones = new ArrayList<>();

        milestones.addAll(issuesService.milestones(repoInfo.owner, repoInfo.name, state.name(), 1));

        for (int i = nextPage; i < lastPage; i++)
            milestones.addAll(issuesService.milestones(repoInfo.owner, repoInfo.name, state.name(), i));

        return milestones;
    }

    private class IssueMilestonesCallback extends BaseInfiniteCallback<List<Milestone>> {

        private List<Milestone> milestones;
        private RepoInfo repoInfo;
        private IssuesService service;

        public IssueMilestonesCallback(RepoInfo repoInfo, IssuesService service) {
            this.repoInfo = repoInfo;
            this.service = service;
            milestones = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.milestones(repoInfo.owner, repoInfo.name, state.name(), this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.milestones(repoInfo.owner, repoInfo.name, state.name(), nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(milestones, null);
            }
        }

        @Override
        protected void response(List<Milestone> milestones) {
            this.milestones.addAll(milestones);
        }
    }

}
