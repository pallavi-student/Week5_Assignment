package com.day2json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class FilterByAge {
        public static void main(String[] args) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/day2json/People.json"));

            List<JsonNode> filteredList = new ArrayList<>();

            for (JsonNode node : rootNode) {
                if (node.get("age").asInt() > 25) {
                    filteredList.add(node);
                }
            }

            String filteredJson = objectMapper.writeValueAsString(filteredList);
            System.out.println(filteredJson);
        }
    }

