package com.alorma.github.sdk.security;

import com.alorma.gitskarios.core.ApiConnection;

/**
 * Created by Bernat on 08/07/2014.
 */
public class GitHub implements ApiConnection {

    public GitHub() {
        if (GithubDeveloperCredentials.getInstance().getProvider() == null) {
            throw new IllegalArgumentException("Credentials provider cannot be null");
        }
    }

    @Override
    public String getApiOauthUrlEndpoint() {
        return "https://github.com";
    }

    @Override
    public String getApiOauthRequest() {
        return "https://github.com/login/oauth/authorize";
    }

    @Override
    public String getApiEndpoint() {
        return "https://api.github.com";
    }

    @Override
    public String getType() {
        return "github";
    }

}
