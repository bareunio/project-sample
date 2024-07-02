package io.bareun.sample.domain.api.service;

import io.bareun.base.api.client.WebApiClient;
import io.bareun.base.api.request.ApiRequestBuilder;
import io.bareun.base.common.dto.map.BaseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {

    @Value("${example.api.host}")
    private String baseUrl;

    private final WebApiClient webApiClient;

    public BaseMap readAll() {
        return webApiClient.callReturn(ApiRequestBuilder.<BaseMap>builder()
                .method(HttpMethod.GET)
                .url(baseUrl + "/users")
                .responseType(BaseMap.class)
                .build());
    }

    public void create() {
        webApiClient.call(ApiRequestBuilder.<BaseMap>builder()
                .method(HttpMethod.POST)
                .header("Content-Type", "application/json")
                .url(baseUrl + "/users")
                .body(new BaseMap("test", 1))
                .responseType(BaseMap.class)
                .build());
    }
}
