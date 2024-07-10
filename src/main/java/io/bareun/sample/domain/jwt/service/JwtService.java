package io.bareun.sample.domain.jwt.service;

import io.bareun.sample.config.jwt.*;
import io.bareun.sample.domain.jwt.dto.JwtCreateRequest;
import io.bareun.sample.domain.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.bareun.sample.config.jwt.JwtType.ACCESS;
import static io.bareun.sample.config.jwt.JwtType.REFRESH;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProvider provider;

    public JwtResponse accessToken(JwtCreateRequest request) {
        JwtCreate access = createJwt(request, ACCESS);
        JwtCreate refresh = createJwt(request, REFRESH);

        return JwtResponse.builder()
                .accessToken(access.getToken())
                .refreshToken(refresh.getToken())
                .accessTokenExpireDate(access.getExpire())
                .refreshTokenExpireDate(refresh.getExpire())
                .build();
    }

    public JwtResponse refreshToken(JwtUserDetails principal) {
        JwtCreateRequest request = JwtCreateRequest.builder()
                .subject(principal.getSubject())
                .scope(principal.getScope())
                .build();

        JwtCreate access = createJwt(request, ACCESS);
        JwtCreate refresh = createJwt(request, REFRESH);

        return JwtResponse.builder()
                .accessToken(access.getToken())
                .refreshToken(refresh.getToken())
                .accessTokenExpireDate(access.getExpire())
                .refreshTokenExpireDate(refresh.getExpire())
                .build();
    }

    private JwtCreate createJwt(JwtCreateRequest request, JwtType type) {
        JwtToken token = JwtToken.builder()
                .subject(request.getSubject())
                .scope(request.getScope())
                .type(type)
                .build();

        return provider.createJwt(token);
    }
}
