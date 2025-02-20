package com.day2json;

import com.google.gson.*;
import java.io.FileReader;

public class FilterJsonDataByAge {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray users = parser.parse(new FileReader("src/main/java/com/day2json/data.json")).getAsJsonArray();

        for (JsonElement userElement : users) {
            JsonObject user = userElement.getAsJsonObject();
            if (user.get("age").getAsInt() > 25) {
                System.out.println(user);
            }
        }
    }
}

