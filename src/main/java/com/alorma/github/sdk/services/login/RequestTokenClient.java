package com.alorma.github.sdk.services.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alorma.github.sdk.security.GitHub;
import com.alorma.gitskarios.core.GitskariosDeveloperCredentials;
import com.alorma.gitskarios.core.bean.dto.request.RequestTokenDTO;
import com.alorma.gitskarios.core.Token;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public class RequestTokenClient extends GithubClient<Token> {
    private String code;

    public RequestTokenClient(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    public void execute() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getClient().getApiOauthUrlEndpoint())
                .setRequestInterceptor(this)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        executeService(restAdapter);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Accept", "application/json");
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        LoginService loginService = restAdapter.create(LoginService.class);


        RequestTokenDTO tokenDTO = new RequestTokenDTO();
        tokenDTO.client_id = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getApiClient();
        tokenDTO.client_secret = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getAPiSecret();
        tokenDTO.redirect_uri = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getApiOauth();
        tokenDTO.code = code;

        loginService.requestToken(tokenDTO, this);
    }

    @NonNull
    private GitHub getConnection() {
        return new GitHub();
    }

    @Override
    protected Token executeServiceSync(RestAdapter restAdapter) {
        LoginService loginService = restAdapter.create(LoginService.class);


        RequestTokenDTO tokenDTO = new RequestTokenDTO();
        tokenDTO.client_id = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getApiClient();
        tokenDTO.client_secret = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getAPiSecret();
        tokenDTO.redirect_uri = GitskariosDeveloperCredentials.getInstance().getProvider(getConnection()).getApiOauth();
        tokenDTO.code = code;

        return loginService.requestToken(tokenDTO);
    }
}
