package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 02/04/2015.
 */
public class GetGistDetailClient extends GithubClient<Gist> {
    private String id;

    public GetGistDetailClient(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(GistsService.class).gistDetail(id, this);
    }

    @Override
    protected Gist executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(GistsService.class).gistDetail(id);
    }
}
