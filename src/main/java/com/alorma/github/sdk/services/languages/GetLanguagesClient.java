package com.alorma.github.sdk.services.languages;

import android.content.Context;

import com.alorma.github.sdk.R;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.Arrays;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 24/09/2015.
 */
public class GetLanguagesClient extends GithubClient<List<String>> {
    private boolean allLanguages;

    public GetLanguagesClient(Context context, boolean allLanguages) {
        super(context);
        this.allLanguages = allLanguages;
    }

    @Override
    public void execute() {
        executeService(null);
    }

    @Override
    public List<String> executeSync() {
        return executeServiceSync(null);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (getOnResultCallback() != null) {
            getOnResultCallback().onResponseOk(executeServiceSync(restAdapter), null);
        }
    }

    @Override
    protected List<String> executeServiceSync(RestAdapter restAdapter) {
        String[] languages = getContext().getResources().getStringArray(allLanguages ? R.array.available_languages : R.array.default_languages);

        return Arrays.asList(languages);
    }
}
