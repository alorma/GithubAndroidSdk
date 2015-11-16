package com.alorma.github.sdk.services.gists;

import com.alorma.github.sdk.bean.dto.request.CommentRequest;
import com.alorma.github.sdk.bean.dto.request.EditGistRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.bean.dto.response.GithubComment;

import java.util.List;

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

/**
 * Created by Bernat on 08/07/2014.
 */
public interface GistsService {

	//Async
	@GET("/gists")
	void userGistsList(Callback<List<Gist>> callback);

	@GET("/gists")
	void userGistsList(@Query("page") int page, Callback<List<Gist>> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, Callback<List<Gist>> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, @Query("page") int page, Callback<List<Gist>> callback);

	@GET("/gists/starred")
	void userStarredGistsList(Callback<List<Gist>> callback);

	@GET("/gists/starred")
	void userStarredGistsList(@Query("page") int page, Callback<List<Gist>> callback);

	@GET("/gists/{id}")
	void gistDetail(@Path("id") String id, Callback<Gist> callback);

	@POST("/gists")
	void publish(@Body Gist gist, Callback<Gist> gistCallback);

	@POST("/gists/{id}/comments")
	void publishComment(@Path("id") String id, @Body CommentRequest body, Callback<GithubComment> gistCallback);

	@PATCH("/gists/{id}")
	void edit(@Path("id") String id, @Body EditGistRequestDTO editGistRequestDTO, Callback<Gist> callback);

	@GET("/gists/{id}/comments")
	void comments(@Path("id") String id, Callback<List<GithubComment>> callback);

	@GET("/gists/{id}/comments")
	void comments(@Path("id") String id, @Query("page") int page, Callback<List<GithubComment>> callback);

	@DELETE("/gists/{id}/comments/{comment_id}")
	void deleteComment(@Path("id") String id, @Path("comment_id") String commentId, Callback<Response> callback);

	@POST("/gists/{id}/comments/{comment_id}")
	void editComment(@Path("id") String gistId, @Path("comment_id") String commentId, @Body CommentRequest body, Callback<GithubComment> callback);

	@GET("/gists/public")
	void publicGistsList(Callback<List<Gist>> callback);

	@GET("/gists/public")
	void publicGistsList(@Query("page") int page, Callback<List<Gist>> callback);


	//Sync
	@GET("/gists")
	List<Gist> userGistsList();

	@GET("/gists")
	List<Gist> userGistsList(@Query("page") int page);

	@GET("/users/{username}/gists")
	List<Gist> userGistsList(@Path("username") String username);

	@GET("/users/{username}/gists")
	List<Gist> userGistsList(@Path("username") String username, @Query("page") int page);

	@GET("/gists/starred")
	List<Gist> userStarredGistsList();

	@GET("/gists/starred")
	List<Gist> userStarredGistsList(@Query("page") int page);

	@GET("/gists/{id}")
	Gist gistDetail(@Path("id") String id);

	@POST("/gists")
	Gist publish(@Body Gist gist);

	@POST("/gists/{id}/comments")
	GithubComment publishComment(@Path("id") String id, @Body CommentRequest body);

	@PATCH("/gists/{id}")
	Gist edit(@Path("id") String id, @Body EditGistRequestDTO editGistRequestDTO);

	@GET("/gists/{id}/comments")
	List<GithubComment> comments(@Path("id") String id);

	@GET("/gists/{id}/comments")
	List<GithubComment> comments(@Path("id") String id, @Query("page") int page);

	@DELETE("/gists/{id}/comments/{comment_id}")
	Response deleteComment(@Path("id") String id, @Path("comment_id") String commentId);

	@POST("/gists/{id}/comments/{comment_id}")
	GithubComment editComment(@Path("id") String gistId, @Path("comment_id") String commentId, @Body CommentRequest body);

	@GET("/gists/public")
	List<Gist> publicGistsList();

	@GET("/gists/public")
	List<Gist> publicGistsList(@Query("page") int page);
}