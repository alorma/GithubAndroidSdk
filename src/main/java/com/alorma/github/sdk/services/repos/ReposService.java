package com.alorma.github.sdk.services.repos;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 08/07/2014.
 */
public interface ReposService {

	//Async
	// User repositories
	@GET("/user/repos?type=owner")
	void userReposList(@Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/user/repos?type=owner")
	void userReposList(@Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/repos?type=owner")
	void userReposList(@Path("username") String username, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/repos?type=owner")
	void userReposList(@Path("username") String username, @Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/user/repos?affiliation=organization_member")
	void userReposListFromOrgs(@Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/user/repos?affiliation=organization_member")
	void userReposListFromOrgs(@Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/orgs/{org}/repos?type=all")
	void orgsReposList(@Path("org") String org, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/orgs/{org}/repos?type=all")
	void orgsReposList(@Path("org") String org, @Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	// Starred repos
	@GET("/user/starred?sort=updated")
	void userStarredReposList(@Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/user/starred?sort=updated")
	void userStarredReposList(@Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/starred?sort=updated")
	void userStarredReposList(@Path("username") String username, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/starred?sort=updated")
	void userStarredReposList(@Path("username") String username, @Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	// Wathched repos
	@GET("/user/subscriptions")
	void userSubscribedReposList(@Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/user/subscriptions")
	void userSubscribedReposList(@Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/subscriptions")
	void userSubscribedReposList(@Path("username") String username, @Query("sort") String sort, Callback<List<Repo>> callback);

	@GET("/users/{username}/subscriptions")
	void userSubscribedReposList(@Path("username") String username, @Query("page") int page, @Query("sort") String sort, Callback<List<Repo>> callback);

	// Member
	@GET("/user/repos?type=member")
	void userMemberRepos(Callback<List<Repo>> callback);
	// Member
	@GET("/user/repos?type=member")
	void userMemberRepos(@Query("page") int page, Callback<List<Repo>> callback);

}
