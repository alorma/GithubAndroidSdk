package com.alorma.github.sdk.services.commit;

import com.alorma.github.sdk.bean.dto.request.CommitCommentRequest;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitComment;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 07/09/2014.
 */
public interface CommitsService {

    //Async
    @GET("/repos/{owner}/{name}/commits/{sha}")
    void singleCommit(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, Callback<Commit> callback);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    void singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, Callback<List<CommitComment>> callback);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    void singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, @Query("page") int page, Callback<List<CommitComment>> callback);


    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("page") int page, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha, @Query("page") int page,
                       Callback<List<Commit>> callback);

    @POST("/repos/{owner}/{name}/commits/{sha}/comments")
    void publishComment(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, @Body CommitCommentRequest request,
                        Callback<CommitComment> callback);


    //Sync
    @GET("/repos/{owner}/{name}/commits/{sha}")
    Commit singleCommit(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    List<CommitComment> singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    List<CommitComment> singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, @Query("page") int page);


    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commits(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commits(@Path("owner") String owner, @Path("name") String repo, @Query("sha") String sha);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, @Query("sha") String sha);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("page") int page);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha);

    @GET("/repos/{owner}/{name}/commits")
    List<Commit> commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha, @Query("page") int page);

    @POST("/repos/{owner}/{name}/commits/{sha}/comments")
    CommitComment publishComment(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, @Body CommitCommentRequest request);

}
