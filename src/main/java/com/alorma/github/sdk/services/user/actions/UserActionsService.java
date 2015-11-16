package com.alorma.github.sdk.services.user.actions;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UserActionsService {

    //Async
    @GET("/repos/{owner}/{name}/collaborators/{username}")
    void checkIfUserIsCollaborator(@Path("owner") String owner, @Path("name") String repo, @Path("username") String username, Callback<Object> callback);


    //Sync
    @GET("/repos/{owner}/{name}/collaborators/{username}")
    Object checkIfUserIsCollaborator(@Path("owner") String owner, @Path("name") String repo, @Path("username") String username);

}
