package com.day2json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
public class ReadJsonAbstractFile {

        public static void main(String[] args) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/day2json/Source.json"));

            // Extract specific fields
            String name = rootNode.get("name").asText();
            String email = rootNode.get("email").asText();

            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        }
    }

