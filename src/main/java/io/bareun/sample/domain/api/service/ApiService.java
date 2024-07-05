package io.bareun.sample.domain.api.service;

import io.bareun.base.api.client.WebApiClient;
import io.bareun.base.api.request.ApiRequest;
import io.bareun.base.api.request.ApiRequestBuilder;
import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.sample.domain.api.dto.PostManJsonApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {

    /**
     * POSTMAN 샘플 API 호스트
     */
    @Value("${example.api.host}")
    private String baseUrl;

    private final WebApiClient webApiClient;

    public BaseMap readAll() {
        // API 요청 객체 생성
        ApiRequest<BaseMap> request = ApiRequestBuilder.<BaseMap>builder()
                .method(HttpMethod.GET)
                .url(baseUrl + "/users")
                .responseType(BaseMap.class)
                .build();

        // 동기 - 외부 API 호출 후 응답 값 반환
        return webApiClient.callReturn(request);
    }

    public void create() {
        // 커스텀 API 요청 객체 생성
        PostManJsonApiRequest request = PostManJsonApiRequest.builder()
                .url(baseUrl + "/users")
                .body(new BaseMap("test", 1))
                .build();

        // 비동기 - 외부 API 호출
        webApiClient.call(request);
    }
}
