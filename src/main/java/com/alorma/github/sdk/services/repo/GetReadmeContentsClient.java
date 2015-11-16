package com.alorma.github.sdk.services.repo;

import android.content.Context;
import android.os.Handler;
import android.util.Base64;

import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.content.GetMarkdownClient;

import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetReadmeContentsClient extends GithubRepoClient<String> {
    
	private OnResultCallback<String> callback;

    public GetReadmeContentsClient(Context context, RepoInfo info) {
        super(context, info);
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (getBranch() == null) {
            repoService.readme(getOwner(), getRepo(), new ContentCallback());
        } else {
            repoService.readme(getOwner(), getRepo(), getBranch(), new ContentCallback());
        }
    }

    @Override
    protected String executeServiceSync(RepoService repoService) {
        Content content;
        if (getBranch() == null) {
            content = repoService.readme(getOwner(), getRepo());
        } else {
            content = repoService.readme(getOwner(), getRepo(), getBranch());
        }

        RequestMarkdownDTO requestMarkdownDTO = new RequestMarkdownDTO();
        if ("base64".equals(content.encoding)) {
            byte[] data = Base64.decode(content.content, Base64.DEFAULT);
            try {
                content.content = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        requestMarkdownDTO.text = content.content;
        GetMarkdownClient markdownClient = new GetMarkdownClient(context, requestMarkdownDTO);
        return markdownClient.executeSync();
    }

    public void setCallback(OnResultCallback<String> callback) {
        this.callback = callback;
    }

    private class ContentCallback implements Callback<Content> {

        @Override
        public void success(Content content, Response response) {
            RequestMarkdownDTO requestMarkdownDTO = new RequestMarkdownDTO();
            if ("base64".equals(content.encoding)) {
                byte[] data = Base64.decode(content.content, Base64.DEFAULT);
                try {
                    content.content = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            requestMarkdownDTO.text = content.content;
            GetMarkdownClient markdownClient = new GetMarkdownClient(context, requestMarkdownDTO);
            markdownClient.setOnResultCallback(callback);
            markdownClient.execute();
        }

        @Override
        public void failure(RetrofitError error) {
            if (callback != null) {
                callback.onFail(error);
            }
        }
    }
}
