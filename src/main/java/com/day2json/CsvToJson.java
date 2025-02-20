package com.day2json;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvToJson {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/day2json/data.csv"));
        String header = br.readLine(); // Read header row
        String[] columns = header.split(",");

        List<JsonObject> jsonList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            JsonObject json = new JsonObject();
            for (int i = 0; i < columns.length; i++) {
                json.addProperty(columns[i], values[i]);
            }
            jsonList.add(json);
        }
        br.close();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(jsonList));
    }
}

