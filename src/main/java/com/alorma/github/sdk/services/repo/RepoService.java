package com.alorma.github.sdk.services.repo;

import com.alorma.github.sdk.bean.dto.request.RepoRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.dto.response.CompareCommit;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.dto.response.Contributor;
import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.GithubStatusResponse;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.MilestoneState;
import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PATCH;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 17/07/2014.
 */
public interface RepoService {

    //Async
    @GET("/repos/{owner}/{name}")
    void get(@Path("owner") String owner, @Path("name") String repo, Callback<Repo> callback);

    @GET("/repos/{owner}/{name}/branches")
    void branches(@Path("owner") String owner, @Path("name") String repo, Callback<List<Branch>> callback);

    @GET("/repos/{owner}/{name}/contents")
    void contents(@Path("owner") String owner, @Path("name") String repo, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/readme")
    void readme(@Path("owner") String owner, @Path("name") String repo, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/readme")
    void readme(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contents(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path, @Query("ref") String ref,  Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo, Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases/latest")
    void lastRelease(@Path("owner") String owner, @Path("name") String repo, Callback<Release> callback);

    @GET("/repos/{owner}/{name}/releases/{id}")
    void release(@Path("owner") String owner, @Path("name") String repo, @Path("id") String id, Callback<Release> callback);

    @GET("/repos/{owner}/{name}/compare/{base}...{head}")
    void compareCommits(@Path("owner") String owner, @Path("name") String repo, @Path("base") String base, @Path("head") String head, Callback<CompareCommit> callback);

    @DELETE("/repos/{owner}/{name}")
    void delete(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @PATCH("/repos/{owner}/{name}")
    void edit(@Path("owner") String owner, @Path("name") String repo, @Body RepoRequestDTO repoRequestDTO, Callback<Repo> callback);

    @GET("/repos/{owner}/{name}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, Callback<List<GithubEvent>> eventsCallback);

    @GET("/repos/{owner}/{name}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<GithubEvent>> eventsCallback);

    @GET("/repos/{owner}/{name}/forks")
    void listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort, Callback<List<Repo>> callback);

    @GET("/repos/{owner}/{name}/forks")
    void listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort, @Query("page") int page, Callback<List<Repo>> callback);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    void combinedStatus(@Path("owner") String owner, @Path("name") String repo, @Path("ref") String ref, Callback<GithubStatusResponse> callback);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    void combinedStatus(@Path("owner") String owner, @Path("name") String repo, @Path("ref") String ref, @Query("page") int page, Callback<GithubStatusResponse> callback);

    //Sync
    @GET("/repos/{owner}/{name}")
    Repo get(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/branches")
    List<Branch> branches(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/contents")
    List<Content> contents(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/contents")
    List<Content> contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/readme")
    Content readme(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/readme")
    Content readme(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/contents/{path}")
    List<Content> contents(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path);

    @GET("/repos/{owner}/{name}/contents/{path}")
    List<Content> contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/stats/contributors")
    List<Contributor> contributors(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/stats/contributors")
    List<Contributor> contributors(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/collaborators")
    List<User> collaborators(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/collaborators")
    List<User> collaborators(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/releases")
    List<Release> releases(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/releases")
    List<Release> releases(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/releases/latest")
    Release lastRelease(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/releases/{id}")
    Release release(@Path("owner") String owner, @Path("name") String repo, @Path("id") String id);

    @GET("/repos/{owner}/{name}/compare/{base}...{head}")
    CompareCommit compareCommits(@Path("owner") String owner, @Path("name") String repo, @Path("base") String base, @Path("head") String head);

    @DELETE("/repos/{owner}/{name}")
    Response delete(@Path("owner") String owner, @Path("name") String repo);

    @PATCH("/repos/{owner}/{name}")
    Repo edit(@Path("owner") String owner, @Path("name") String repo, @Body RepoRequestDTO repoRequestDTO);

    @GET("/repos/{owner}/{name}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page);

    @GET("/repos/{owner}/{name}/forks")
    List<Repo> listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort);

    @GET("/repos/{owner}/{name}/forks")
    List<Repo> listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort, @Query("page") int page);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    GithubStatusResponse combinedStatus(@Path("owner") String owner, @Path("name") String repo, @Path("ref") String ref);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    GithubStatusResponse combinedStatus(@Path("owner") String owner, @Path("name") String repo, @Path("ref") String ref, @Query("page") int page);

}
