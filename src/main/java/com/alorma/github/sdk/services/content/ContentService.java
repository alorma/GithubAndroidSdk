package com.alorma.github.sdk.services.content;

import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.bean.dto.response.Content;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 22/07/2014.
 */
public interface ContentService {

    //Async
    @POST("/markdown/raw")
    void markdown(@Body String readme, Callback<String> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void fileContent(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void fileContentSha(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path, @Query("sha") String sha, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void fileContentRef(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path, @Query("ref") String ref, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/{file_type}/{path}")
    void archiveLink(@Path("owner") String owner, @Path("name") String repo, @Path("file_type") String file_type, @Path(value="path", encode = false) String path, Callback<Object> callback);

    //Sync
    @POST("/markdown/raw")
    String markdown(@Body String readme);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Content fileContent(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Content fileContentSha(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path, @Query("sha") String sha);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Content fileContentRef(@Path("owner") String owner, @Path("name") String repo, @Path(value="path", encode = false) String path, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/{file_type}/{path}")
    Object archiveLink(@Path("owner") String owner, @Path("name") String repo, @Path("file_type") String file_type, @Path(value="path", encode = false) String path);
}
