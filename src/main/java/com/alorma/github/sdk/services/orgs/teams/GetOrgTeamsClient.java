package com.alorma.github.sdk.services.orgs.teams;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Team;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 04/09/2014.
 */
public class GetOrgTeamsClient extends GithubClient<List<Team>> {
    private String org;
    private int page = -1;

    public GetOrgTeamsClient(Context context, String org) {
        super(context);
        this.org = org;
    }

    public GetOrgTeamsClient(Context context, String org, int page) {
        super(context);
        this.org = org;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        TeamsService orgsService = restAdapter.create(TeamsService.class);
        if (page == -1) {
            orgsService.teams(org, this);
        } else {
            orgsService.teams(org, page, this);
        }
    }

    @Override
    protected List<Team> executeServiceSync(RestAdapter restAdapter) {
        TeamsService orgsService = restAdapter.create(TeamsService.class);
        if (page == -1) {
            return orgsService.teams(org);
        } else {
            return orgsService.teams(org, page);
        }
    }
}
