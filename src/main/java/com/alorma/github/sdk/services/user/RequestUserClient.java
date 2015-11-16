package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 12/07/2014.
 */
public class RequestUserClient extends GithubUsersClient<User> {

    private String username;

    public RequestUserClient(Context context, String username) {
        super(context);
        this.username = username;
    }

    @Override
    protected void executeService(UsersService usersService) {
        usersService.getSingleUser(username, this);
    }

    @Override
    protected User executeServiceSync(UsersService usersService) {
        return usersService.getSingleUser(username);
    }
}
