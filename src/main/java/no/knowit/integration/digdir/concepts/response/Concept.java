package no.knowit.integration.digdir.concepts.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import no.knowit.integration.digdir.concepts.response.deserialize.AltLabelDeserializer;
import no.knowit.integration.digdir.concepts.response.deserialize.SubjectDeserializer;

import java.util.Map;

public record Concept(
        String id,
        Definition definition,
        Map<String, String> prefLabel,

        @JsonDeserialize(using = AltLabelDeserializer.class)
        Map<String, String> altLabel,
        @JsonDeserialize(using = SubjectDeserializer.class) Map<String, String> subject
) {
}
