package io.bareun.sample.config;

import io.bareun.base.api.client.WebApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConifg {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebApiClient webApiClient() {
        return new WebApiClient(webClientBuilder());
    }
}
