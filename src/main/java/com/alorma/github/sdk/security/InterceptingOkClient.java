package com.alorma.github.sdk.security;

import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.gitskarios.core.client.BaseClient;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

public class InterceptingOkClient extends OkClient {

    private BaseClient baseClient;

    public InterceptingOkClient(OkHttpClient client, BaseClient baseClient) {
        super(client);
        this.baseClient = baseClient;
    }

    @Override
    public Response execute(Request request) throws IOException {

        Response response = super.execute(request);
        try {
            for (Header header : response.getHeaders()) {
                if (header.getName().equals("Link")) {
                    String[] parts = header.getValue().split(",");
                    for (String part : parts) {
                        PaginationLink bottomPaginationLink = new PaginationLink(part);
                        if (bottomPaginationLink.rel == RelType.last) {
                            baseClient.last = bottomPaginationLink.uri;
                            baseClient.lastPage = bottomPaginationLink.page;
                        } else if (bottomPaginationLink.rel == RelType.next) {
                            baseClient.next = bottomPaginationLink.uri;
                            baseClient.nextPage = bottomPaginationLink.page;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
