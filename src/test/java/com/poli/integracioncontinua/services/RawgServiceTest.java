package com.poli.integracioncontinua.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RawgServiceTest {

    private RawgService rawgService;

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.rawgService = new RawgService(webClient, "YOUR_API_KEY");

        // Mocking the WebClient chain
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        // Mock the bodyToMono method
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Mock Response"));
    }

    @Test
    void getDeveloperInfoTest() {
        String developerId = "123";

        Mono<String> result = rawgService.getDeveloperInfo(developerId);

        StepVerifier.create(result)
                .expectNext("Mock Response")
                .verifyComplete();
    }
}
