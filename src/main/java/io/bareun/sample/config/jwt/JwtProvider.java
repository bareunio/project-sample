package io.bareun.sample.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Date;

import static io.bareun.sample.config.jwt.JwtType.ACCESS;
import static io.bareun.sample.config.jwt.JwtType.REFRESH;
import static io.jsonwebtoken.Jwts.parser;
import static java.lang.String.join;
import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.security.key:NOp+vt6xPFIfG3ZWlp8N7a6B8nB/94fh7rByuugmVFE=}")
    private String key;

    @Value("${jwt.expire.access:1}")
    private Long accessExpire;

    @Value("${jwt.expire.refresh:13}")
    private Long refreshExpire;

    private final JwtUserDetailsService userDetailsService;

    public JwtCreate createJwt(JwtToken token) {
        LocalDateTime now = now();
        LocalDateTime expire = now.plusHours(expireHour(token.getType()));
        SecretKey secretKey = getSecretKey();

        return JwtCreate.builder()
                .token(Jwts.builder()
                        .subject(String.valueOf(token.getSubject()))
                        .audience()
                            .add(token.getScope().name())
                            .and()
                        .issuer(token.getType().name())
                        .issuedAt(toDate(now))
                        .expiration(toDate(expire))
                        .signWith(secretKey)
                        .compact())
                .expire(expire)
                .build();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public void validateToken(String jwt) {
        if (!hasText(jwt)) {
            throw new AuthenticationCredentialsNotFoundException("토큰정보가 존재하지 않습니다.");
        }

        Claims claims = getClaims(jwt);
        JwtToken token = getToken(claims);

        if (validateExpire(claims)) {
            throw new AccessDeniedException("만료된 토큰 값입니다.");
        }

        if (REFRESH.equals(token.getType())) {
            //TODO : DB에서 검증 - refresh-token
            //tokenService.validateRefreshToken(token, jwt);
        }
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
    }

    private Claims getClaims(String jwt) {
        return parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    private JwtToken getToken(Claims claims) {
        return JwtToken.builder()
                .subject(Long.valueOf(claims.getSubject()))
                .scope(JwtRole.valueOf(getAudience(claims)))
                .type(JwtType.valueOf(claims.getIssuer()))
                .build();
    }

    private String getAudience(Claims claims) {
        return claims.getAudience().stream().findAny()
                .orElseThrow(() -> new BadCredentialsException("토큰 정보 불일치"));
    }

    private boolean validateExpire(Claims claims) {
        return claims.getExpiration().before(toDate(now()));
    }

    private Date toDate(LocalDateTime time) {
        return valueOf(time);
    }

    private long expireHour(JwtType type) {
        return ACCESS.equals(type) ? accessExpire : refreshExpire;
    }

    private String getUsername(String jwt) {
        Claims claims = getClaims(jwt);

        return join("/t", claims.getSubject(), getAudience(claims));
    }
}
