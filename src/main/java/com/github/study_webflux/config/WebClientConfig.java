package com.github.study_webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        final HttpClient httpClient = HttpClient.create().protocol(HttpProtocol.HTTP11, HttpProtocol.H2C);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .filter((request, next) -> {
                    var compressedRequest = ClientRequest.from(request)
                            .header(HttpHeaders.CONTENT_ENCODING, "gzip")
                            .build();
                    return next.exchange(compressedRequest);
                }).build();
    }
}
