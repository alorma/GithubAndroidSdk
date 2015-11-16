package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 03/10/2014.
 */
public class GetAuthUserClient extends GithubUsersClient<User> {
    private String accessToken;

    public GetAuthUserClient(Context context) {
        super(context);
    }
    public GetAuthUserClient(Context context, String accessToken) {
        super(context);
        this.accessToken = accessToken;
    }

    @Override
    protected void executeService(UsersService usersService) {
        usersService.me(this);
    }

    @Override
    protected User executeServiceSync(UsersService usersService) {
        return usersService.me();
    }

    @Override
    protected String getToken() {
        if (accessToken != null) {
            return accessToken;
        } else {
            return super.getToken();
        }
    }
}
