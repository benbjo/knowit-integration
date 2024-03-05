package no.knowit.integration.digdir.concepts.response;

import java.util.Date;
import java.util.Map;

public record Definition(Map<String, String> text, Date lastUpdated
) { }
