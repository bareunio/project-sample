package io.bareun.sample.domain.jwt.dto;

import io.bareun.sample.config.jwt.JwtRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class JwtCreateRequest {

    @NotEmpty
    private Long subject;

    @NotEmpty
    private JwtRole scope;

    @Builder
    public JwtCreateRequest(Long subject, JwtRole scope) {
        this.subject = subject;
        this.scope = scope;
    }
}
