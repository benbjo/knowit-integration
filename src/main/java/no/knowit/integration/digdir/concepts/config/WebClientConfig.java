package no.knowit.integration.digdir.concepts.config;

import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
class WebClientConfig {

    @Bean
    public WebClient webClient() {
        String baseUrl = "https://search.fellesdatakatalog.digdir.no/concepts";

        HttpClient httpClient = HttpClient.create()
                .wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
        ClientHttpConnector conn = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(conn)
                .build();
    }
}