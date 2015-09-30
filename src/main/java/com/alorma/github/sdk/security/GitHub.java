package com.alorma.github.sdk.security;

import android.net.Uri;

import com.alorma.gitskarios.core.ApiConnection;
import com.alorma.gitskarios.core.GitskariosDeveloperCredentials;

/**
 * Created by Bernat on 08/07/2014.
 */
public class GitHub implements ApiConnection {

    private static final String SCOPES = "gist,user,notifications,repo,delete_repo";

    public GitHub() {

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

    @Override
    public Uri buildUri(Uri callbackUri) {
        String url = String.format("%s?client_id=%s&scope=" + SCOPES,
                getApiOauthRequest(),
                GitskariosDeveloperCredentials.getInstance().getProvider(this).getApiClient());
        return Uri.parse(url).buildUpon().appendQueryParameter("redirect_uri", callbackUri.toString())
                .build();
    }
}
