package no.knowit.integration.service;

import no.knowit.integration.digdir.concepts.response.Concept;
import no.knowit.integration.digdir.concepts.response.Definition;
import no.knowit.integration.dto.BegrepDTO;
import no.knowit.integration.dto.BegrepDefinisjonDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ConceptToBegrepDTOMapper implements Mapper<Concept, BegrepDTO>{

    @Override
    public BegrepDTO map(Concept source, String languageCode) {

        BegrepDTO.Builder builder = BegrepDTO.builder();

        builder.id(source.id());
        builder.definition(createBegrepDefinisjonDTO(source.definition(), languageCode));
        builder.prefLabel(getText(source.prefLabel(), languageCode));
        builder.altLabel(getText(source.altLabel(), languageCode));
        builder.subject(getText(source.subject(), languageCode));

        return builder.build();
    }

    private static String getText(Map<String, String> textByLanguageCode, String languageCode) {
        return Optional.ofNullable(textByLanguageCode)
                .map(texts -> texts.get(languageCode))
                .orElse(null);
    }

    private BegrepDefinisjonDTO createBegrepDefinisjonDTO(Definition source, String languageCode) {
        return BegrepDefinisjonDTO.builder()
                .tekst(Optional.ofNullable(source)
                        .map(definition -> getText(definition.text(), languageCode))
                        .orElse(null))
                .lastUpdated(Optional.ofNullable(source)
                        .map(Definition::lastUpdated)
                        .orElse(null)
                )
                .build();
    }
}
