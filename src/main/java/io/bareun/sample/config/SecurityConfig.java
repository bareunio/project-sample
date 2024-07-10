package io.bareun.sample.config;

import io.bareun.sample.config.jwt.JwtAuthenticationFilter;
import io.bareun.sample.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.GET, "/jwt/authenticated").hasAnyRole("USER")
                        .anyRequest().permitAll()  // 모든 요청을 허용
                )
                .addFilterBefore(new JwtAuthenticationFilter(provider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
