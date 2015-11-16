package com.alorma.github.sdk.services.repo;

import android.content.Context;
import android.util.Pair;
import com.alorma.github.sdk.bean.dto.request.RepoRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by bernat.borras on 11/11/15.
 */
public class CreateRepositoryClient extends GithubClient<Repo> {

  private RepoRequestDTO repoRequestDTO;

  public CreateRepositoryClient(Context context, RepoRequestDTO repoRequestDTO) {
    super(context);
    this.repoRequestDTO = repoRequestDTO;
  }

  @Override
  public Observable<Pair<Repo, Response>> observable() {
    return getRestAdapter().create(RepoService.class).createObs(repoRequestDTO).map(
        new Func1<Repo, Pair<Repo, Response>>() {
          @Override
          public Pair<Repo, Response> call(Repo repo) {
            return new Pair<>(repo, null);
          }
        });
  }

  @Override
  protected void executeService(RestAdapter restAdapter) {
    restAdapter.create(RepoService.class).create(repoRequestDTO, this);
  }

  @Override
  protected Repo executeServiceSync(RestAdapter restAdapter) {
    return restAdapter.create(RepoService.class).create(repoRequestDTO);
  }
}
