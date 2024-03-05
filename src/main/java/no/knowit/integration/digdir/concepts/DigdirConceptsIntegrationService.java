package no.knowit.integration.digdir.concepts;


import no.knowit.integration.digdir.concepts.request.ConceptsRequestBody;
import no.knowit.integration.digdir.concepts.response.Concept;
import no.knowit.integration.digdir.concepts.response.ConceptsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigdirConceptsIntegrationService {
    private final Logger logger = LoggerFactory.getLogger(DigdirConceptsIntegrationService.class);
    private final WebClient webClient;

    public DigdirConceptsIntegrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Concept> fetchAllObjects() {
        return getTotalPages()
                .flatMapMany(this::fetchPages)
                .flatMap(Flux::fromIterable)
                .collectList()
                .block();
    }

    private Mono<Integer> getTotalPages() {
        return webClient
                .post()
                .retrieve()
                .bodyToMono(ConceptsResponse.class)
                .map(response -> response.page().totalPages())
                .onErrorResume(e -> {
                    logger.error("Error occurred while fetching total pages", e);
                    return Mono.just(0);
                });
    }

    private Flux<List<Concept>> fetchPages(int totalPages) {
        logger.info("Fetching Concepts from {} pages", totalPages);
        return Flux.range(0, totalPages)
                .parallel()
                .flatMap(this::fetchPage)
                .sequential();
    }

    private Mono<List<Concept>> fetchPage(int page) {
        ConceptsRequestBody requestBody = new ConceptsRequestBody(page);

        logger.info("Fetching Concepts from page {}", page);
        return webClient
                .post()
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ConceptsResponse.class)
                .map(ConceptsResponse::hits)
                .defaultIfEmpty(new ArrayList<>())
                .onErrorResume(e -> {
                    logger.error("Error occurred while fetching page {}", page, e);
                    return Mono.just(new ArrayList<>());
                });
    }
}
