package com.alorma.github.sdk.services.git;

import com.alorma.github.sdk.bean.dto.response.GitBlob;
import com.alorma.github.sdk.bean.dto.response.GitCommit;
import com.alorma.github.sdk.bean.dto.response.GitReference;
import com.alorma.github.sdk.bean.dto.response.GitTree;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GitDataService {

    //Async
    //Reference
    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name, Callback<List<GitReference>> callback);

    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name, @Query("page") int page, Callback<List<GitReference>> callback);

    @GET("/repos/{owner}/{repo}/git/{ref}")
    void repoReference(@Path("owner") String owner, @Path("repo") String name, @Path(value = "ref", encode = false) String ref, Callback<GitReference> callback);

    @GET("/repos/{owner}/{repo}/git/commits/{sha}")
    void repoCommit(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha, Callback<GitCommit> callback);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}")
    void repoTree(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha, Callback<GitTree> callback);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}?recursive=1")
    void repoTreeRecursive(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha, Callback<GitTree> callback);

    @GET("/repos/{owner}/{repo}/git/blobs/{sha}")
    void repoBlob(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha, Callback<GitBlob> callback);

    //Sync
    @GET("/repos/{owner}/{repo}/git/refs")
    List<GitReference> repoReferences(@Path("owner") String owner, @Path("repo") String name);

    @GET("/repos/{owner}/{repo}/git/refs")
    List<GitReference> repoReferences(@Path("owner") String owner, @Path("repo") String name, @Query("page") int page);

    @GET("/repos/{owner}/{repo}/git/{ref}")
    GitReference repoReference(@Path("owner") String owner, @Path("repo") String name,  @Path(value = "ref", encode = false) String ref);

    @GET("/repos/{owner}/{repo}/git/commits/{sha}")
    GitCommit repoCommit(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}")
    GitTree repoTree(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}?recursive=1")
    GitTree repoTreeRecursive(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/blobs/{sha}")
    GitBlob repoBlob(@Path("owner") String owner, @Path("repo") String name, @Path("sha") String sha);
}
