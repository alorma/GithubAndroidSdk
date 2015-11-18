package com.alorma.github.sdk.services.git;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.GitTree;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import rx.Observable;

public class GetGitTreeClient extends GithubClient<GitTree> {

    private final RepoInfo info;
    private final boolean recursive;

    public GetGitTreeClient(Context context, RepoInfo repoInfo, boolean recursive) {
        super(context);
        this.info = repoInfo;
        this.recursive = recursive;
    }

    @Override
    protected Observable<GitTree> getApiObservable(RestAdapter restAdapter) {
        GitDataService gitDataService = restAdapter.create(GitDataService.class);
        if (recursive) {
            return gitDataService
                .repoTreeRecursive(info.owner, info.name, info.branch);
        } else {
            return gitDataService
                .repoTree(info.owner, info.name, info.branch);
        }
    }
}
