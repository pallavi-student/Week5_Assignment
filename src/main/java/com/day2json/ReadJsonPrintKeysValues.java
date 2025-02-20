package com.day2json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.util.Map;

public class ReadJsonPrintKeysValues {
    public static void main(String[] args) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(new FileReader("src/main/java/com/day2json/Source.json")).getAsJsonObject();

        // Iterate through keys and values
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

