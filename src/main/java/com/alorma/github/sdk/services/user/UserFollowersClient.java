package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 14/07/2014.
 */
public class UserFollowersClient extends GithubListClient<List<User>> {

    private String username;
    private int page = 0;

    public UserFollowersClient(Context context, String username) {
        super(context);
        this.username = username;
    }

    public UserFollowersClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                UsersService usersService = restAdapter.create(UsersService.class);
                if (page == 0) {
                    if (username == null) {
                        usersService.followers(this);
                    } else {
                        usersService.followers(username, this);
                    }
                } else {
                    if (username == null) {
                        usersService.followers(page, this);
                    } else {
                        usersService.followers(username, page, this);
                    }
                }
            }
        };
    }
}
