package com.alorma.github.sdk.services.repo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.content.GetMarkdownClient;
import java.io.UnsupportedEncodingException;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetReadmeContentsClient extends GithubRepoClient<String> {

    public GetReadmeContentsClient(Context context, RepoInfo info) {
        super(context, info);
    }

    @Override
    protected Observable<String> getApiObservable(RestAdapter restAdapter) {
        RepoService repoService = restAdapter.create(RepoService.class);

        Observable<Content> contentObservable;

        if (getBranch() == null) {
            contentObservable = repoService.readme(getOwner(), getRepo());
        } else {
            contentObservable = repoService.readme(getOwner(), getRepo(), getBranch());
        }

        return contentObservable.filter(new Func1<Content, Boolean>() {
            @Override
            public Boolean call(Content content) {
                return content != null && !TextUtils.isEmpty(content.content);
            }
        }).filter(new Func1<Content, Boolean>() {
            @Override
            public Boolean call(Content content) {
                return "base64".equals(content.encoding);
            }
        }).map(new Func1<Content, String>() {
            @Override
            public String call(Content content) {
                byte[] data = Base64.decode(content.content, Base64.DEFAULT);
                try {
                    content.content = new String(data, "UTF-8");
                    return content.content;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !TextUtils.isEmpty(s);
            }
        }).map(new Func1<String, RequestMarkdownDTO>() {
            @Override
            public RequestMarkdownDTO call(String s) {
                RequestMarkdownDTO requestMarkdownDTO = new RequestMarkdownDTO();
                requestMarkdownDTO.text = s;
                return requestMarkdownDTO;
            }
        }).flatMap(new Func1<RequestMarkdownDTO, Observable<String>>() {
            @Override
            public Observable<String> call(RequestMarkdownDTO requestMarkdownDTO) {
                return new GetMarkdownClient(context, requestMarkdownDTO).observable();
            }
        });
    }
}
