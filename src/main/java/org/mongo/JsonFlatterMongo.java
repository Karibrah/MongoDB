package org.mongo;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonFlatterMongo {

    public static void main(String[] args) {
        JsonFlatterMongo jsonFlatter = new JsonFlatterMongo();
        String jsonString = jsonFlatter.readInput();
        JsonProcessor jsonProcessor =  new JsonProcessor();
        if (jsonProcessor.isValidJsonFile(jsonString))
            jsonProcessor.flatJsonFile(jsonString);

    }

    private String readInput() {
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
            return jsonStringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error no file passed to the app");
        }
        return null;
    }

}
