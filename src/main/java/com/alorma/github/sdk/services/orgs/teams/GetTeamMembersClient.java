package com.alorma.github.sdk.services.orgs.teams;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;
import retrofit.RestAdapter;

public class GetTeamMembersClient extends GithubListClient<List<User>> {

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
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                TeamsService teamsService = restAdapter.create(TeamsService.class);
                if (page == 0) {
                    teamsService.members(id, this);
                } else {
                    teamsService.members(id, page, this);
                }
            }
        };
    }
}
