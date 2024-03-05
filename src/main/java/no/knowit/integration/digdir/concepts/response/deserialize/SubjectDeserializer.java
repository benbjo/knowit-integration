package no.knowit.integration.digdir.concepts.response.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubjectDeserializer extends StdDeserializer<Map<String, String>> {
    public SubjectDeserializer() {
        this(null);
    }
    protected SubjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        Map<String, String> subjectMap = new HashMap<>();

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node != null && node.isArray()) {
            for (JsonNode item : node) {
                JsonNode labelNode = item.get("label");
                if (labelNode != null && labelNode.isObject()) {
                    labelNode.fields().forEachRemaining(entry -> {
                        if (!subjectMap.containsKey(entry.getKey())) {
                            subjectMap.put(entry.getKey(), entry.getValue().textValue());
                        }
                    });
                }
            }
        }

        return subjectMap;
    }
}
