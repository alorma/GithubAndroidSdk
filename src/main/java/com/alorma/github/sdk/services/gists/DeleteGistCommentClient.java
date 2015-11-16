package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;

public class DeleteGistCommentClient extends GithubClient<Response> {

    private String id;
    private String commentId;

    public DeleteGistCommentClient(Context context, String commentId, String id) {
        super(context);
        this.commentId = commentId;
        this.id = id;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(GistsService.class).deleteComment(id, commentId, this);
    }

    @Override
    protected Response executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(GistsService.class).deleteComment(id, commentId);
    }
}
