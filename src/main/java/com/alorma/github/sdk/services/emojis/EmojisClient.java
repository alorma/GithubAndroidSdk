package com.alorma.github.sdk.services.emojis;

import android.content.Context;

import com.alorma.github.sdk.services.client.GithubClient;

import java.util.HashMap;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 08/07/2015.
 */
public class EmojisClient extends GithubClient<HashMap<String, String>> {

    public EmojisClient(Context context) {
        super(context);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(EmojisService.class).getEmojis(this);
    }

    @Override
    protected HashMap<String, String> executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(EmojisService.class).getEmojis();
    }

}
