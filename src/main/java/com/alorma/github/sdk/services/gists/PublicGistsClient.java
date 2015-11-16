package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

public class PublicGistsClient extends GithubClient<List<Gist>> {
    private int page = 0;

    public PublicGistsClient(Context context) {
        super(context);
    }
    public PublicGistsClient(Context context, int page) {
        super(context);
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        if (page == 0) {
            gistsService.publicGistsList(this);
        } else {
            gistsService.publicGistsList(page, this);
        }
    }

    @Override
    protected List<Gist> executeServiceSync(RestAdapter restAdapter) {
        GistsService gistsService = restAdapter.create(GistsService.class);
        if (page == 0) {
            return gistsService.publicGistsList();
        } else {
            return gistsService.publicGistsList(page);
        }
    }

}
