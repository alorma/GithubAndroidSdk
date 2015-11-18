package com.alorma.github.sdk.services.content;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.FileInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 10/09/2014.
 */
public class GetFileContentClient extends GithubClient<Content> {

    private FileInfo fileInfo;

    public GetFileContentClient(Context context, FileInfo fileInfo) {
        super(context);
        this.fileInfo = fileInfo;
    }

    @Override
    protected Observable<Content> getApiObservable(RestAdapter restAdapter) {
        ContentService contentService = restAdapter.create(ContentService.class);

        if (fileInfo.head != null) {
            return contentService.fileContentSha(fileInfo.repoInfo.owner, fileInfo.repoInfo.name, fileInfo.path, fileInfo.head);
        } else if (fileInfo.repoInfo.branch != null) {
            return contentService.fileContentRef(fileInfo.repoInfo.owner, fileInfo.repoInfo.name, fileInfo.path, fileInfo.repoInfo.branch);
        } else {
            return contentService.fileContent(fileInfo.repoInfo.owner, fileInfo.repoInfo.name, fileInfo.path);
        }
    }
}
