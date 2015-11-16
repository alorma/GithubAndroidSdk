package com.alorma.github.sdk.services.gtignore;

import com.alorma.github.sdk.bean.dto.response.GitIgnoreTemplates;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Bernat on 28/09/2014.
 */
public interface GitIgnoreService {

	//Async
	@GET("/gitignore/templates")
	void list(Callback<GitIgnoreTemplates> callback);

	//Sync
	@GET("/gitignore/templates")
	GitIgnoreTemplates list();

	//Observable
	@GET("/gitignore/templates")
	Observable<GitIgnoreTemplates> listObs();
}
