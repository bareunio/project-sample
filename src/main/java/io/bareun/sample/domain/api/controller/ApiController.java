package io.bareun.sample.domain.api.controller;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.sample.domain.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/users")
    public ApiResponse<BaseMap> readAll() {
        return ApiResponse.success(apiService.readAll());
    }

    @PostMapping("/async/users")
    public ApiResponse<Void> create() {
        apiService.create();
        return ApiResponse.success();
    }
}
