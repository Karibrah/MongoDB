package org.mongo;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonFlatterMongo {

    public static void main(String[] args) {
        JsonFlatterMongo jsonFlatter = new JsonFlatterMongo();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = null;
            StringBuffer jsonStringBuffer = new StringBuffer();
            while (true) {
                if ((line = reader.readLine()) != null) {
                    jsonStringBuffer.append(line);
                } else {
                    break;
                }
            }
            String jsonString = jsonStringBuffer.toString();
            if (jsonFlatter.isValidJsonFile(jsonString))
                jsonFlatter.flatJsonFile(jsonString);
        } catch (Exception e) {
            System.out.println("Error no file passed to the app");
        }
    }

    public boolean isValidJsonFile(String json) {
        if (json != null) {
            try {
                JsonParser parser = new JsonParser();
                parser.parse(json);
                return true;
            } catch (Exception e) {
                log("Error validating json : " + json );
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
