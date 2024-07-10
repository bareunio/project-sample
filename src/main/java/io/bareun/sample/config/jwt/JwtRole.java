package io.bareun.sample.config.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtRole {
    ROLE_USER("사용자"), ROLE_ADMIN("관리자");

    private final String description;

}
