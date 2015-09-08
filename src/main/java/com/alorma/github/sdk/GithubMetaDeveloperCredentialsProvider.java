package com.alorma.github.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.alorma.gitskarios.core.client.credentials.DeveloperCredentialsProvider;
import com.alorma.gitskarios.core.client.credentials.MetaDeveloperCredentialsProvider;

/**
 * Created by Bernat on 07/07/2015.
 */
public class GithubMetaDeveloperCredentialsProvider extends MetaDeveloperCredentialsProvider {

    public GithubMetaDeveloperCredentialsProvider(Context context) {
        super(context);
    }

    @Override
    protected String getApiClientKey() {
        return "com.alorma.github.sdk.client";
    }

    @Override
    protected String getApiSecretKey() {
        return "com.alorma.github.sdk.secret";
    }

    @Override
    protected String getApiCallbackKey() {
        return "com.alorma.github.sdk.oauth";
    }

}
