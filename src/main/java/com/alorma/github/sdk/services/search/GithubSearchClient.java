package com.alorma.github.sdk.services.search;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.dto.response.search.UsersSearch;
import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 08/08/2014.
 */
public abstract class GithubSearchClient<Search, K> extends GithubListClient<K> {
    protected String query;
    private int page = 0;

    public GithubSearchClient(Context context, String query) {
        super(context);
        this.query = query;
    }
    public GithubSearchClient(Context context, String query, int page) {
        super(context);
        this.query = query;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public abstract class SearchCallback implements Callback<Search> {

        protected Callback<K> callback;

        public SearchCallback(Callback<K> callback) {

            this.callback = callback;
        }

        @Override
        public void failure(RetrofitError error) {
            callback.failure(error);
        }
    }
}
