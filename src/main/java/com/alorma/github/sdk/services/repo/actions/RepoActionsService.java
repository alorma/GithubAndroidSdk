package com.alorma.github.sdk.services.repo.actions;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 07/08/2014.
 */
public interface RepoActionsService {

    //Async
    @GET("/user/starred/{owner}/{name}")
    void checkIfRepoIsStarred(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @Headers("Content-Length: 0")
    @PUT("/user/starred/{owner}/{name}")
    void starRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty, Callback<Response> callback);

    @DELETE("/user/starred/{owner}/{name}")
    void unstarRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @GET("/user/subscriptions/{owner}/{name}")
    void checkIfRepoIsWatched(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @Headers("Content-Length: 0")
    @PUT("/user/subscriptions/{owner}/{name}")
    void watchRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty, Callback<Object> callback);

    @DELETE("/user/subscriptions/{owner}/{name}")
    void unwatchRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @Headers("Content-Length: 0")
    @POST("/repos/{owner}/{name}/forks")
    void forkRepo(@Path("owner") String owner, @Path("name") String repo, @Body Object empty, Callback<Repo> callback);

    @Headers("Content-Length: 0")
    @POST("/repos/{owner}/{name}/forks")
    void forkRepo(@Path("owner") String owner, @Path("name") String repo, @Query("organization") String org, @Body Object empty, Callback<Repo> callback);


    //Sync
    @GET("/user/starred/{owner}/{name}")
    Response checkIfRepoIsStarred(@Path("owner") String owner, @Path("name") String repo);

    @Headers("Content-Length: 0")
    @PUT("/user/starred/{owner}/{name}")
    Response starRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty);

    @DELETE("/user/starred/{owner}/{name}")
    Response unstarRepo(@Path("owner") String owner, @Path("name") String repo);

    @GET("/user/subscriptions/{owner}/{name}")
    Response checkIfRepoIsWatched(@Path("owner") String owner, @Path("name") String repo);

    @Headers("Content-Length: 0")
    @PUT("/user/subscriptions/{owner}/{name}")
    Response watchRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty);

    @DELETE("/user/subscriptions/{owner}/{name}")
    Response unwatchRepo(@Path("owner") String owner, @Path("name") String repo);

    @Headers("Content-Length: 0")
    @POST("/repos/{owner}/{name}/forks")
    Repo forkRepo(@Path("owner") String owner, @Path("name") String repo, @Body Object empty);

    @Headers("Content-Length: 0")
    @POST("/repos/{owner}/{name}/forks")
    Repo forkRepo(@Path("owner") String owner, @Path("name") String repo, @Query("organization") String org, @Body Object empty);
}
