package io.bareun.sample.domain.api.dto;

import io.bareun.base.api.request.JsonApiRequest;
import io.bareun.base.common.dto.map.BaseMap;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class PostManJsonApiRequest implements JsonApiRequest<BaseMap> {

    private final String url;
    private final Object body;

    private final Class<BaseMap> responseType = BaseMap.class;

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = JsonApiRequest.super.getHeaders();

        headers.add("API-KEY", "abcdefg");

        return headers;
    }

    @Override
    public Consumer<BaseMap> getSubscribe() {
        return (response) -> log.info("PostManJsonApiRequest subscribe response: {}", response);
    }
}
