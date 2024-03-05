
package no.knowit.integration.digdir.concepts.response.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AltLabelDeserializer extends StdDeserializer<Map<String, String>> {
    public AltLabelDeserializer() {
        this(null);
    }
    protected AltLabelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        Map<String, String> altLabelMap = new HashMap<>();

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node != null && node.isArray()) {
            for (JsonNode item : node) {
                if (item.isObject()) {
                    item.fields().forEachRemaining(entry -> {
                        if (!altLabelMap.containsKey(entry.getKey())) {
                            altLabelMap.put(entry.getKey(), entry.getValue().textValue());
                        }
                    });
                }
            }
        }

        return altLabelMap;
    }
}
