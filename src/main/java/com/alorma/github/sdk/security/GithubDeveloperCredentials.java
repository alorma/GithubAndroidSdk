package com.alorma.github.sdk.security;

import com.alorma.gitskarios.core.client.credentials.DeveloperCredentialsProvider;

/**
 * Created by Bernat on 07/07/2015.
 */
public class GithubDeveloperCredentials {

    private static GithubDeveloperCredentials githubDeveloperCredentials;
    private DeveloperCredentialsProvider provider;

    public static void init(DeveloperCredentialsProvider provider) {
        getInstance().provider = provider;
    }

    public static GithubDeveloperCredentials getInstance() {
        if (githubDeveloperCredentials == null) {
            githubDeveloperCredentials = new GithubDeveloperCredentials();
        }
        return githubDeveloperCredentials;
    }

    private GithubDeveloperCredentials() {

    }

    public DeveloperCredentialsProvider getProvider() {
        return provider;
    }
}
