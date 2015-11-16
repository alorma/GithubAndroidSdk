package com.alorma.github.sdk.services.issues;

import com.alorma.github.sdk.bean.dto.request.CommentRequest;
import com.alorma.github.sdk.bean.dto.request.CreateMilestoneRequestDTO;
import com.alorma.github.sdk.bean.dto.request.EditIssueRequestDTO;
import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by Bernat on 22/08/2014.
 */
public interface IssuesService {

    //Async
    @GET("/repos/{owner}/{name}/issues?sort=updated")
    void issues(@Path("owner") String owner, @Path("name") String repo, @QueryMap Map<String, String> filter, Callback<List<Issue>> callback);

    @GET("/repos/{owner}/{name}/issues?sort=updated")
    void issues(@Path("owner") String owner, @Path("name") String repo, @QueryMap Map<String, String> filter, @Query("page") int page, Callback<List<Issue>> callback);

    @GET("/issues")
    void issues(@QueryMap Map<String, String> filter, Callback<List<Issue>> callback);

    @GET("/issues")
    void issues(@QueryMap Map<String, String> filter, @Query("page") int page, Callback<List<Issue>> callback);

    @POST("/repos/{owner}/{name}/issues")
    void create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue, Callback<Issue> callback);

    @GET("/repos/{owner}/{name}/issues/{num}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<Issue> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<List<GithubComment>> callback);

	@GET("/repos/{owner}/{name}/issues/{num}/events")
	void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,Callback<List<GithubEvent>> callback);

	@GET("/repos/{owner}/{name}/issues/{num}/events")
	void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<List<GithubEvent>> callback);

	@PATCH("/repos/{owner}/{name}/issues/{num}")
	void closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest, Callback<Issue> callback);

	@POST("/repos/{owner}/{name}/issues/{num}/comments")
	void addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body GithubComment comment, Callback<GithubComment> callback);

    @GET("/repos/{owner}/{name}/milestones") //State can be open, closed and all
    void milestones(@Path("owner") String owner, @Path("name") String repo, @Query("state") String state, Callback<List<Milestone>> callback);

    @GET("/repos/{owner}/{name}/milestones") //State can be open, closed and all
    void milestones(@Path("owner") String owner, @Path("name") String repo, @Query("state") String state, @Query("page") int page, Callback<List<Milestone>> callback);

    @GET("/repos/{owner}/{name}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, Callback<List<Label>> callback);

    @GET("/repos/{owner}/{name}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Label>> callback);

    @GET("/repos/{owner}/{name}/assignees")
    void assignees(@Path("owner") String owner, @Path("name") String repo, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/assignees")
    void assignees(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<User>> callback);

    @POST("/repos/{owner}/{name}/milestones")
    void createMilestone(@Path("owner") String owner, @Path("name") String repo, @Body CreateMilestoneRequestDTO createMilestoneRequestDTO, Callback<Milestone> callback);

    @PATCH("/repos/{owner}/{name}/issues/{number}")
    void editIssue(@Path("owner") String owner, @Path("name") String repo, @Path("number") int number, @Body EditIssueRequestDTO editIssueRequestDTO, Callback<Issue> callback);

    @DELETE("/repos/{owner}/{name}/issues/comments/{id}")
    void deleteComment(@Path("owner") String owner, @Path("name") String name, @Path("id") String id, Callback<Response> callback);

    @PATCH("/repos/{owner}/{name}/issues/comments/{id}")
    void editComment(@Path("owner") String owner, @Path("name") String name, @Path("id") String id, @Body CommentRequest body, Callback<GithubComment> callback);


    //Sync
    @GET("/repos/{owner}/{name}/issues?sort=updated")
    List<Issue> issues(@Path("owner") String owner, @Path("name") String repo, @QueryMap Map<String, String> filter);

    @GET("/repos/{owner}/{name}/issues?sort=updated")
    List<Issue> issues(@Path("owner") String owner, @Path("name") String repo, @QueryMap Map<String, String> filter, @Query("page") int page);

    @GET("/issues")
    List<Issue> issues(@QueryMap Map<String, String> filter);

    @GET("/issues")
    List<Issue> issues(@QueryMap Map<String, String> filter, @Query("page") int page);

    @POST("/repos/{owner}/{name}/issues")
    Issue create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue);

    @GET("/repos/{owner}/{name}/issues/{num}")
    Issue detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    Issue closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    GithubComment addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body GithubComment comment);

    @GET("/repos/{owner}/{name}/milestones") //State can be open, closed and all
    List<Milestone> milestones(@Path("owner") String owner, @Path("name") String repo, @Query("state") String state);

    @GET("/repos/{owner}/{name}/milestones") //State can be open, closed and all
    List<Milestone> milestones(@Path("owner") String owner, @Path("name") String repo, @Query("state") String state, @Query("page") int page);

    @GET("/repos/{owner}/{name}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/assignees")
    List<User> assignees(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/assignees")
    List<User> assignees(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @POST("/repos/{owner}/{name}/milestones")
    Milestone createMilestone(@Path("owner") String owner, @Path("name") String repo, @Body CreateMilestoneRequestDTO createMilestoneRequestDTO);

    @PATCH("/repos/{owner}/{name}/issues/{number}")
    Issue editIssue(@Path("owner") String owner, @Path("name") String repo, @Path("number") int number, @Body EditIssueRequestDTO editIssueRequestDTO);

    @DELETE("/repos/{owner}/{name}/issues/comments/{id}")
    Response deleteComment(@Path("owner") String owner, @Path("name") String name, @Path("id") String id);

    @PATCH("/repos/{owner}/{name}/issues/comments/{id}")
    GithubComment editComment(@Path("owner") String owner, @Path("name") String name, @Path("id") String id, @Body CommentRequest body);
}
