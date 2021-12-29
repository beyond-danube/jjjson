package io.siniavtsev.jjjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class JsonSorter {

    private static ObjectMapper mapper = JsonMapper.builder()
            .nodeFactory(new SortingNodeFactory())
            .build();

    static class SortingNodeFactory extends JsonNodeFactory {
        @Override
        public ObjectNode objectNode() {
            return new ObjectNode(this, new TreeMap<String, JsonNode>());
        }
    }

    public static String getSortedJson(String json) throws JsonProcessingException {
        JsonNode root = mapper.reader().readTree(json);

        recursivelySortArrays(root);

        return mapper.writeValueAsString(root);
    }

    public static void setJsonWriteWithIndent(boolean indent) {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, indent);
    }

    static void recursivelySortArrays(JsonNode jsonRootNode) {
        jsonRootNode.forEach(entry -> {
            if (entry.isArray()) {
                ArrayNode arrayNode = (ArrayNode) entry;

                Iterator<JsonNode> i = arrayNode.elements();
                List<JsonNode> list = new ArrayList<>();
                while(i.hasNext()){
                    list.add(i.next());
                }

                list.sort(Comparator.comparing(JsonNode::toString));

                ((ArrayNode) entry).removeAll().addAll(list);

                entry.forEach(JsonSorter::recursivelySortArrays);
            } else {
                recursivelySortArrays(entry);
            }
        });
    }
}
