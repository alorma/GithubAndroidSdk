package com.alorma.github.sdk.services.user;

import com.alorma.github.sdk.bean.dto.response.Email;
import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 12/07/2014.
 */
public interface UsersService {

	//Async
	@GET("/users/{user}")
	void getSingleUser(@Path("user") String user, Callback<User> callback);

	@GET("/user/emails")
	void userEmails(Callback<List<Email>> callback);

	// Followers
	@GET("/user/followers")
	void followers(Callback<List<User>> callback);

	@GET("/users/{username}/followers")
	void followers(@Path("username") String username, Callback<List<User>> callback);

	@GET("/user/followers")
	void followers(@Query("page") int page, Callback<List<User>> callback);

	@GET("/users/{username}/followers")
	void followers(@Path("username") String username, @Query("page") int page, Callback<List<User>> callback);

	// Following
	@GET("/user/following")
	void following(Callback<List<User>> callback);

	@GET("/users/{username}/following")
	void following(@Path("username") String username, Callback<List<User>> callback);

	@GET("/user/following")
	void following(@Query("page") int page, Callback<List<User>> callback);

	@GET("/users/{username}/following")
	void following(@Path("username") String username, @Query("page") int page, Callback<List<User>> callback);

	@GET("/user")
	void me(Callback<User> userCallback);

	// FOLLOWING USER

	@GET("/user/following/{username}")
	void checkFollowing(@Path("username") String username, Callback<Object> callback);

	@PUT("/user/following/{username}")
	void followUser(@Body String empty, @Path("username") String username, Callback<Object> callback);

	@DELETE("/user/following/{username}")
	void unfollowUser(@Path("username") String username, Callback<Object> callback);


	//ORGS MEMBERS

	@GET("/orgs/{org}/members")
	void orgMembers(@Path("org") String org, Callback<List<User>> callback);

	@GET("/orgs/{org}/members")
	void orgMembers(@Path("org") String org, @Query("page") int page, Callback<List<User>> callback);


	//Sync
	@GET("/users/{user}")
	User getSingleUser(@Path("user") String user);

	@GET("/user/emails")
	List<Email> userEmails();

	// Followers
	@GET("/user/followers")
	List<User> followers();

	@GET("/users/{username}/followers")
	List<User> followers(@Path("username") String username);

	@GET("/user/followers")
	List<User> followers(@Query("page") int page);

	@GET("/users/{username}/followers")
	List<User> followers(@Path("username") String username, @Query("page") int page);

	// Following
	@GET("/user/following")
	List<User> following();

	@GET("/users/{username}/following")
	List<User> following(@Path("username") String username);

	@GET("/user/following")
	List<User> following(@Query("page") int page);

	@GET("/users/{username}/following")
	List<User> following(@Path("username") String username, @Query("page") int page);

	@GET("/user")
	User me();

	// FOLLOWING USER

	@GET("/user/following/{username}")
	Object checkFollowing(@Path("username") String username);

	@PUT("/user/following/{username}")
	Object followUser(@Body String empty, @Path("username") String username);

	@DELETE("/user/following/{username}")
	Object unfollowUser(@Path("username") String username);


	//ORGS MEMBERS

	@GET("/orgs/{org}/members")
	List<User> orgMembers(@Path("org") String org);

	@GET("/orgs/{org}/members")
	List<User> orgMembers(@Path("org") String org, @Query("page") int page);
}
