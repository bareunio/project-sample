package io.bareun.sample.domain.jwt.controller;

import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.sample.config.jwt.JwtUserDetails;
import io.bareun.sample.domain.jwt.dto.JwtCreateRequest;
import io.bareun.sample.domain.jwt.dto.JwtResponse;
import io.bareun.sample.domain.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/access-token")
    public ApiResponse<?> accessToken(@RequestBody JwtCreateRequest request) {
        JwtResponse jwtResponse = jwtService.accessToken(request);
        return ApiResponse.success(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ApiResponse<?> refreshToken(@AuthenticationPrincipal JwtUserDetails principal) {
        JwtResponse jwtResponse = jwtService.refreshToken(principal);
        return ApiResponse.success(jwtResponse);
    }

    @GetMapping("/authenticated")
    public ApiResponse<?> isAuthenticated() {
        return ApiResponse.success("Completed");
    }

}
