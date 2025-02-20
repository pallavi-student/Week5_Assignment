package com.day2json;

import com.google.gson.*;
import java.io.FileReader;

public class MergeTwoJsonFiles {
    public static void main(String[] args) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject json1 = parser.parse(new FileReader("src/main/java/com/day2/data.json")).getAsJsonObject();
        JsonObject json2 = parser.parse(new FileReader("src/main/java/com/day2/person.json")).getAsJsonObject();

        json1.entrySet().forEach(entry -> json2.add(entry.getKey(), entry.getValue()));

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(json2));
    }
}

