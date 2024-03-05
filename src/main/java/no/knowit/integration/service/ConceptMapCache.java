package no.knowit.integration.service;

import no.knowit.integration.digdir.concepts.DigdirConceptsIntegrationService;
import no.knowit.integration.digdir.concepts.response.Concept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConceptMapCache {

    private final Logger logger = LoggerFactory.getLogger(ConceptMapCache.class);
    private final DigdirConceptsIntegrationService digdirConceptsIntegrationService;

    public ConceptMapCache(DigdirConceptsIntegrationService digdirConceptsIntegrationService) {
        this.digdirConceptsIntegrationService = digdirConceptsIntegrationService;
    }

    @Cacheable(value = "concepts")
    public Map<String, Concept> getAllConcepts() {
        logger.info("Start fetching all concepts from Digdir and updating cache");
        List<Concept> concepts = digdirConceptsIntegrationService.fetchAllObjects();

        return concepts.stream()
                .collect(Collectors.toMap(Concept::id, concept -> concept, (concept1, concept2) -> concept1));
    }

    @CacheEvict(value = "concepts", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.conceptsTTL}")
    public void emptyCache() {
        logger.info("Emptying cache");
    }
}
