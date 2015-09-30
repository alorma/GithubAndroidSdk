package com.alorma.github.sdk.services.notifications;

import com.alorma.github.sdk.bean.dto.request.LastDate;
import com.alorma.github.sdk.bean.dto.response.Notification;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 18/02/2015.
 */
public interface NotificationsService {

	//Async
	@GET("/notifications")
	void getNotifications(Callback<List<Notification>> notifications);
	
	@PUT("/repos/{owner}/{name}/notifications")
	void markAsReadRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Response> responseCallback);

	@PUT("/repos/{owner}/{name}/notifications")
	void markAsReadRepo(@Path("owner") String owner, @Path("name") String repo, @Body LastDate body, Callback<Response> responseCallback);

	@PATCH("/notifications/threads/{id}")
	void markThreadAsRead(@Path("id") String id, @Body Object empty, Callback<Response> callback);

	@PUT("/notifications/threads/{id}/subscription")
	void subscribeThread(@Path("id") String id, @Query("subscribed") boolean subscribed, @Query("ignored") boolean ignored, Callback<Response> callback);

	@DELETE("/notifications/threads/{id}/subscription")
	void unsubscribeThread(@Path("id") String id, Callback<Response> callback);

	//Sync
	@GET("/notifications")
	List<Notification> getNotifications();

	@PUT("/repos/{owner}/{name}/notifications")
	Response markAsReadRepo(@Path("owner") String owner, @Path("name") String repo);

	@PUT("/repos/{owner}/{name}/notifications")
	Response markAsReadRepo(@Path("owner") String owner, @Path("name") String repo, @Body LastDate body);

	@PATCH("/notifications/threads/{id}")
	Response markThreadAsRead(@Path("id") String id, @Body Object empty);

	@PUT("/notifications/threads/{id}/subscription")
	Response subscribeThread(@Path("id") String id, @Query("subscribed") boolean subscribed, @Query("ignored") boolean ignored);

	@DELETE("/notifications/threads/{id}/subscription")
	Response unsubscribeThread(@Path("id") String id);
}
