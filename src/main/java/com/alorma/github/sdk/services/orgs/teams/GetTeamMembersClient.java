package com.alorma.github.sdk.services.orgs.teams;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.Team;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.pullrequest.PullRequestsService;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class GetTeamMembersClient extends GithubClient<List<User>> {

    private final String id;
    private final int page;

    public GetTeamMembersClient(Context context, String id) {
        this(context, id,  0);
    }

    public GetTeamMembersClient(Context context, String id, int page) {
        super(context);
        this.id = id;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        TeamsService teamsService = restAdapter.create(TeamsService.class);
        teamsService.members(id, new MembersCallback(id, teamsService, getOnResultCallback()));
    }

    @Override
    protected List<User> executeServiceSync(RestAdapter restAdapter) {
        TeamsService teamsService = restAdapter.create(TeamsService.class);
        if (page == 0) {
            return teamsService.members(this.id);
        } else {
            return teamsService.members(this.id, page);
        }
    }

    private class MembersCallback extends BaseInfiniteCallback<List<User>> {
        private String id;
        private TeamsService teamsService;
        private final OnResultCallback<List<User>> onResultCallback;
        private List<User> members;

        public MembersCallback(String id, TeamsService teamsService, OnResultCallback<List<User>> onResultCallback) {
            this.id = id;
            this.teamsService = teamsService;
            this.onResultCallback = onResultCallback;
            members = new ArrayList<>();
        }

        @Override
        protected void executePaginated(int nextPage) {
            teamsService.members(this.id, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (onResultCallback != null) {
                onResultCallback.onResponseOk(this.members, null);
            }
        }

        @Override
        protected void response(List<User> members) {
            this.members.addAll(members);
        }

        @Override
        public void execute() {
            if (page == 0) {
                teamsService.members(this.id, this);
            } else {
                teamsService.members(this.id, page, this);
            }
        }
    }


}
