package com.alorma.github.sdk.services.emojis;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Bernat on 08/07/2015.
 */
public interface EmojisService {

    // Async
    @GET("/emojis")
    void getEmojis(Callback<HashMap<String, String>> callback);

    // Sync
    @GET("/emojis")
    HashMap<String, String> getEmojis();

}
