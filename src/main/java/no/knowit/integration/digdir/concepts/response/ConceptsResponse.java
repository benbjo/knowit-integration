package no.knowit.integration.digdir.concepts.response;

import java.util.List;

public record ConceptsResponse(List<Concept> hits, Page page) {

}
