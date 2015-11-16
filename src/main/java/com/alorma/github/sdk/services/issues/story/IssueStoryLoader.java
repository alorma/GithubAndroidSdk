package com.alorma.github.sdk.services.issues.story;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryComparators;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.gitskarios.core.client.PaginationLink;
import com.alorma.gitskarios.core.client.RelType;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class IssueStoryLoader extends GithubClient<IssueStory> {

    private final IssueInfo issueInfo;
    private final String owner;
    private final String repo;
    private final int num;
    private IssueStoryService issueStoryService;

    public IssueStoryLoader(Context context, IssueInfo info) {
        super(context);
        this.issueInfo = info;
        this.owner = issueInfo.repoInfo.owner;
        this.repo = issueInfo.repoInfo.name;
        this.num = issueInfo.num;
        issueStoryService = getRestAdapter().create(IssueStoryService.class);
    }

    @Override
    protected Observable<IssueStory> getApiObservable(RestAdapter restAdapter) {
        return getIssueStory();
    }

    @NonNull
    @RxLogObservable
    private Observable<IssueStory> getIssueStory() {
        return Observable.zip(getIssueObservable(), getIssueDetailsObservable(),
            new Func2<Issue, List<IssueStoryDetail>, IssueStory>() {
                @Override
                public IssueStory call(Issue issue, List<IssueStoryDetail> details) {
                    IssueStory issueStory = new IssueStory();
                    issueStory.issue = issue;
                    issueStory.details = details;
                    Collections.sort(issueStory.details,
                        IssueStoryComparators.ISSUE_STORY_DETAIL_COMPARATOR);
                    return issueStory;
                }
            });
    }

    @RxLogObservable
    private Observable<Issue> getIssueObservable() {
        return issueStoryService.detailObs(owner, repo, num).subscribeOn(Schedulers.io());
    }

    @RxLogObservable
    private Observable<List<IssueStoryDetail>> getIssueDetailsObservable() {
        Observable<IssueStoryDetail> commentsDetailsObs = getCommentsDetailsObs();
        Observable<IssueStoryDetail> eventDetailsObs = getEventDetailsObs();
        return Observable.mergeDelayError(commentsDetailsObs, eventDetailsObs).toList();
    }

    @NonNull
    @RxLogObservable
    private Observable<List<GithubComment>> getCommentsObs() {
        return Observable.create(new BaseInfiniteCallback<List<GithubComment>>() {

            @Override
            public void execute() {
                issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, this);
            }

            @Override
            protected void executePaginated(int nextPage) {
                issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, this);
            }

        });
    }

    @RxLogObservable
    private Observable<IssueStoryDetail> getCommentsDetailsObs() {
        return getCommentsObs().subscribeOn(Schedulers.io())
            .flatMap(new Func1<List<GithubComment>, Observable<IssueStoryDetail>>() {
                @Override
                public Observable<IssueStoryDetail> call(List<GithubComment> githubComments) {
                    return Observable.from(githubComments)
                        .map(new Func1<GithubComment, IssueStoryDetail>() {
                            @Override
                            public IssueStoryDetail call(GithubComment githubComment) {
                                long time = getMilisFromDateClearDay(githubComment.created_at);
                                IssueStoryComment detail = new IssueStoryComment(githubComment);
                                detail.created_at = time;
                                return detail;
                            }
                        });
                }
            });
    }

    @NonNull
    @RxLogObservable
    private Observable<List<IssueEvent>> getEventsObs() {
        return Observable.create(new BaseInfiniteCallback<List<IssueEvent>>() {
            @Override
            public void execute() {
                issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, this);
            }

            @Override
            protected void executePaginated(int nextPage) {
                issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, nextPage, this);
            }
        });
    }

    @NonNull
    @RxLogObservable
    private Observable<IssueStoryDetail> getEventDetailsObs() {
        return getEventsObs().subscribeOn(Schedulers.io())
            .flatMap(new Func1<List<IssueEvent>, Observable<IssueStoryDetail>>() {
                @Override
                public Observable<IssueStoryDetail> call(List<IssueEvent> issueEvents) {
                    return Observable.from(issueEvents).filter(new Func1<IssueEvent, Boolean>() {
                        @Override
                        public Boolean call(IssueEvent issueEvent) {
                            return validEvent(issueEvent.event);
                        }
                    }).map(new Func1<IssueEvent, IssueStoryDetail>() {
                        @Override
                        public IssueStoryDetail call(IssueEvent issueEvent) {
                            long time = getMilisFromDateClearDay(issueEvent.created_at);
                            IssueStoryEvent detail = new IssueStoryEvent(issueEvent);
                            detail.created_at = time;
                            return detail;
                        }
                    });
                }
            });
    }

    private long getMilisFromDateClearDay(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        DateTime dt = formatter.parseDateTime(createdAt);

        return dt.minuteOfDay().roundFloorCopy().getMillis();
    }

    private boolean validEvent(String event) {
        return !(event.equals("mentioned") ||
            event.equals("subscribed") ||
            event.equals("unsubscribed") ||
            event.equals("labeled") ||
            event.equals("unlabeled"));
    }

    @Override
    public void log(String message) {
        Log.i("IssueStoryLoader", message);
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.full+json";
    }
}
