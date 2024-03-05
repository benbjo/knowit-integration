package no.knowit.integration.service;

import no.knowit.integration.dto.BegrepDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BegrepService {
    private final ConceptToBegrepDTOMapper mapper;

    private final ConceptMapCache conceptCache;

    @Value("${app.default.languageCode}")
    private String defaultLanguageCode;

    public BegrepService(ConceptToBegrepDTOMapper mapper, ConceptMapCache conceptCache) {
        this.mapper = mapper;
        this.conceptCache = conceptCache;
    }

    public List<BegrepDTO> getAllBegrep(String languageCode) {
        return conceptCache.getAllConcepts().values()
                .stream()
                .map(concept -> mapper.map(concept, getLanguageCode(languageCode)))
                .collect(Collectors.toList());

    }

    public BegrepDTO getBegrepById(String id, String languageCode) {
        return Optional.ofNullable(conceptCache.getAllConcepts().get(id))
                .map(concept -> mapper.map(concept, getLanguageCode(languageCode)))
                .orElseThrow(() -> new NoSuchElementException(String.format("Begrep with id=%s not found", id)));
    }

    private String getLanguageCode(String languageCode) {
        return Optional.ofNullable(languageCode).orElse(defaultLanguageCode);
    }
}
