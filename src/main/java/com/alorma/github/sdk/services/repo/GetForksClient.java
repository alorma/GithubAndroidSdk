package com.alorma.github.sdk.services.repo;

import android.content.Context;
import android.support.annotation.StringDef;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.info.RepoInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by a557114 on 05/09/2015.
 */
public class GetForksClient extends GithubRepoClient<List<Repo>> {

    private final int page;
    private String sort = null;

    public GetForksClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }
    public GetForksClient(Context context, RepoInfo repoInfo, int page) {
        super(context, repoInfo);
        this.page = page;
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (page == 0) {
            repoService.listForks(getOwner(), getRepo(),sort, this);
        } else {
            repoService.listForks(getOwner(), getRepo(), sort, 0, this);
        }
    }

    @Override
    protected List<Repo> executeServiceSync(RepoService repoService) {
        if (page == 0) {
            return repoService.listForks(getOwner(), getRepo(), sort);
        } else {
            return repoService.listForks(getOwner(), getRepo(), sort, 0);
        }
    }

    // newest, oldest, stargazers

    public static final String NEWEST = "newest";
    public static final String OLDEST = "oldest";
    public static final String STARGAZERS = "stargazers";

    @StringDef({NEWEST, OLDEST, STARGAZERS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SORT{}

    public void setSort(@SORT String sort) {
        this.sort = sort;
    }
}
