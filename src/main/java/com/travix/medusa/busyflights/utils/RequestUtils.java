package com.travix.medusa.busyflights.utils;

import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Map;
import java.util.Objects;

public final class RequestUtils {

    private RequestUtils() {
    }

    /**
     * Build request object.
     *
     * @param url             the endpoint where call request
     * @param queryParameters the map with the query parameters
     *
     * @return {@link Request} object
     */
    public static Request builder(String url, Map<String, String> queryParameters) {
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (queryParameters != null) {
            for (Map.Entry<String, String> queryParameter : queryParameters.entrySet()) {
                httpBuilder.addQueryParameter(queryParameter.getKey(), String.valueOf(queryParameter.getValue()));
            }
        }
        return new Request.Builder().url(httpBuilder.build()).build();

    }
}
