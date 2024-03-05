package no.knowit.integration.digdir.concepts.response.deserialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectDeserializerTest {
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Map.class, new SubjectDeserializer());
        objectMapper.registerModule(module);
    }

    @Test
    void testDeserializeWhenLabelContainsMultipleLanguages() throws IOException {
        String jsonString = "[{\"label\": { \"nb\": \"Norwegian Bokmål\", \"nn\": \"Norwegian Nynorsk\",\"en\": \"English\"}}]";

        Map<String, String> result = objectMapper.readValue(jsonString, new TypeReference<>() {
        });

        assertEquals(3, result.size());
        assertEquals("English", result.get("en"));
        assertEquals("Norwegian Bokmål", result.get("nb"));
        assertEquals("Norwegian Nynorsk", result.get("nn"));
    }

    @Test
    void testDeserializeWhenThereAreMultipleLabelsWithOneLanguage() throws IOException {
        String jsonString = "[{\"label\": {\"nb\": \"Norwegian Bokmål\"}},{\"label\": {\"nn\": \"Norwegian Nynorsk\"}},{\"label\": {\"en\": \"English\"}}]";

        Map<String, String> result = objectMapper.readValue(jsonString, new TypeReference<>() {
        });

        assertEquals(3, result.size());
        assertEquals("English", result.get("en"));
        assertEquals("Norwegian Bokmål", result.get("nb"));
        assertEquals("Norwegian Nynorsk", result.get("nn"));
    }

    @Test
    void testDeserializeWhenThereAreMultipleLabelsWithMultipleLanguage() throws IOException {
        String jsonString = "[{\"label\": { \"nb\": \"Norwegian Bokmål\", \"nn\": \"Norwegian Nynorsk\",\"en\": \"English\"}},{\"label\": { \"nb\": \"ShouldNotBeMapped\", \"nn\": \"ShouldNotBeMapped\",\"en\": \"ShouldNotBeMapped\"}}]\n";

        Map<String, String> result = objectMapper.readValue(jsonString, new TypeReference<>() {
        });

        assertEquals(3, result.size());
        assertEquals("English", result.get("en"));
        assertEquals("Norwegian Bokmål", result.get("nb"));
        assertEquals("Norwegian Nynorsk", result.get("nn"));
    }
}