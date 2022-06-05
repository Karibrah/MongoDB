package org.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonProcessor {

    private boolean isArrayExist(String json, ObjectMapper mapper) throws JsonMappingException, JsonProcessingException {

        try {
            JsonNode jsonNode = mapper.readTree(json);
            iterateJsonValues(jsonNode,new ArrayList<>());
        } catch (RuntimeException e){
            log("Error array exist in Json");
            return false;
        } catch (Exception e){
            log("Error processing Json file");
            return false;
        }

        return true;
    }

    private void iterateJsonValues(JsonNode jsonNode, List<String> keys) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                keys.add(field.getKey());
                iterateJsonValues((JsonNode) field.getValue(), keys);
            });
        } else if (jsonNode.isArray()) {
           throw new RuntimeException("Error array exist in Json");
        }
    }

    public boolean isValidJsonFile(String json) {
        if (json != null) {
            try {
                return isArrayExist(json, new ObjectMapper());
            } catch (Exception e) {
                log("Error validating json : " + json);
                log(e.toString());
            }
            return false;
        }
        return false;
    }

    public String flatJsonFile(String json) {
        if (json != null) {
            try {
                String flattenedJson = JsonFlattener.flatten(json);
                prettyPrintJson(flattenedJson);
                return flattenedJson;
            } catch (Exception e) {
                log("Error flatting json: " + json);
                log(e.toString());
            }
        }
        return null;
    }

    private void prettyPrintJson(String json) {
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = parser.parseString(json);
        String prettyJsonString = gson.toJson(je);
        log(prettyJsonString);
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
