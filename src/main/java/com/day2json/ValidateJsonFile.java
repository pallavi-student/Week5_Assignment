package com.day2json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ValidateJsonFile {
        public static void main(String[] args) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();

            String json = "{\"name\":\"Bheem\", \"age\":18}";

            // Parse JSON
            JsonNode node = objectMapper.readTree(json);

            // Validate required fields
            if (node.has("name") && node.has("age")) {
                System.out.println("JSON Structure validated");
            } else {
                System.out.println("JSON Structure not valid");
            }
        }
    }

