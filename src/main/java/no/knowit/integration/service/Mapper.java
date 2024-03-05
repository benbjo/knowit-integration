package no.knowit.integration.service;

public interface Mapper<S, T> {


    T map(S source, String languageCode);
}