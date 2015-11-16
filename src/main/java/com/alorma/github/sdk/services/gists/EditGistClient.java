package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.EditGistRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class EditGistClient extends GithubClient<Gist> {

    private String id;
    private EditGistRequestDTO editGistRequestDTO;

    public EditGistClient(Context context, String id, EditGistRequestDTO editGistRequestDTO) {
        super(context);
        this.id = id;
        this.editGistRequestDTO = editGistRequestDTO;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(GistsService.class).edit(id, editGistRequestDTO, this);
    }

    @Override
    protected Gist executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(GistsService.class).edit(id, editGistRequestDTO);
    }
}
