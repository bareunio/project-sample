package io.bareun.sample.domain.logging.controller;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.common.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/logging")
public class LoggingController {

    @GetMapping("/get/{id}")
    public ApiResponse<Void> get(@PathVariable Long id, @RequestParam Map<String, String> params) {
        return ApiResponse.success();
    }

    @PostMapping("/post")
    public ApiResponse<Void> post(@RequestBody BaseMap body) {
        return ApiResponse.success();
    }
}
