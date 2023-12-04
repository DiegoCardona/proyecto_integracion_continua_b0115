package com.poli.integracioncontinua.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RawgService {

    private final WebClient webClient;
    private final String apiKey;

    public RawgService(@Value("${rawg.api.baseurl}") String baseUrl,
                       @Value("${rawg.api.key}") String apiKey) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    /**
     * Contructor for testing
     * 
     * @param webClient
     * @param apiKey
     */
     public RawgService(WebClient webClient,
                       @Value("${rawg.api.key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public Mono<String> getDeveloperInfo(String developerId) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/developers/{id}")
                                            .queryParam("key", apiKey)
                                            .build(developerId))
            .retrieve()
            .bodyToMono(String.class)
            .onErrorResume(e -> Mono.just("Error: " + e.getMessage())); 
    }
}
