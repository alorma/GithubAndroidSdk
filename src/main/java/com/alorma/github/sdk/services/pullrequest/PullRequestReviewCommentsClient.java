package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.ReviewComment;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 25/07/2015.
 */
public class PullRequestReviewCommentsClient extends GithubClient<List<ReviewComment>> {

    private IssueInfo info;

    public PullRequestReviewCommentsClient(Context context, IssueInfo info) {
        super(context);
        this.info = info;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        PullRequestsService service = restAdapter.create(PullRequestsService.class);
        new ReviewCommentsCallback(context, info, service, getOnResultCallback()).execute();
    }

    @Override
    protected List<ReviewComment> executeServiceSync(RestAdapter restAdapter) {
        PullRequestsService service = restAdapter.create(PullRequestsService.class);
        List<ReviewComment> comments = new ArrayList<>();

        comments.addAll(service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, 1));

        for (int i = nextPage; i < lastPage; i++)
            comments.addAll(service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, i));

        return comments;
    }

    private class ReviewCommentsCallback extends BaseInfiniteCallback<List<ReviewComment>> {

        private final Context context;
        private final IssueInfo info;
        private PullRequestsService pullRequestsService;
        private final OnResultCallback<List<ReviewComment>> onResultCallback;
        private List<ReviewComment> comments;

        public ReviewCommentsCallback(Context context, IssueInfo info, PullRequestsService pullRequestsService, OnResultCallback<List<ReviewComment>> onResultCallback) {
            this.context = context;
            this.info = info;
            this.pullRequestsService = pullRequestsService;
            this.onResultCallback = onResultCallback;
            comments = new ArrayList<>();
        }

        @Override
        protected void executePaginated(int nextPage) {
            pullRequestsService.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (onResultCallback != null) {
                onResultCallback.onResponseOk(comments, null);
            }
        }

        @Override
        protected void response(List<ReviewComment> reviewComments) {
            comments.addAll(reviewComments);
        }

        @Override
        public void execute() {
            pullRequestsService.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.full+json";
    }
}
