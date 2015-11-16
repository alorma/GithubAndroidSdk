package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.CommitInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 07/09/2014.
 */
public class ListCommitsClient extends GithubClient<List<Commit>> {
    private CommitInfo info;
    private String path;
    private int page;

    public ListCommitsClient(Context context, CommitInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    public ListCommitsClient(Context context, CommitInfo info, String path, int page) {
        super(context);
        this.info = info;
        this.path = path;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        CommitsService commitsService = restAdapter.create(CommitsService.class);
        if (path == null) {
            if (info.sha == null) {
                if (page == 0) {
                    commitsService.commits(info.repoInfo.owner, info.repoInfo.name, this);
                } else {
                    commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page, this);
                }
            } else {
                if (page == 0) {
                    commitsService.commits(info.repoInfo.owner, info.repoInfo.name, info.sha, this);
                } else {
                    commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page, info.sha, this);
                }
            }
        } else {
            if (info.sha == null) {
                if (page == 0) {
                    commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, this);
                } else {
                    commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, page, this);
                }
            } else {
                if (page == 0) {
                    commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha, this);
                } else {
                    commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha, page, this);
                }
            }
        }
    }

    @Override
    protected List<Commit> executeServiceSync(RestAdapter restAdapter) {
        CommitsService commitsService = restAdapter.create(CommitsService.class);
        if (path == null) {
            if (info.sha == null) {
                if (page == 0) {
                    return  commitsService.commits(info.repoInfo.owner, info.repoInfo.name);
                } else {
                    return  commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page);
                }
            } else {
                if (page == 0) {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name, info.sha);
                } else {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page, info.sha);
                }
            }
        } else {
            if (info.sha == null) {
                if (page == 0) {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path);
                } else {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, page);
                }
            } else {
                if (page == 0) {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha);
                } else {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha, page);
                }
            }
        }
    }
}
