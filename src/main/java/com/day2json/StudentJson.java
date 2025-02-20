package com.day2json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class StudentJson {
            public static void main(String[] args) throws Exception {
                ObjectMapper objectMapper = new ObjectMapper();

                // Creating a Student JSON object using a Map
                Map<String, Object> student = new HashMap<>();
                student.put("name", "John Doe");
                student.put("age", 20);
                student.put("subjects", Arrays.asList("Math", "Physics", "Computer Science"));

                // Convert to JSON String
                String jsonString = objectMapper.writeValueAsString(student);
                System.out.println(jsonString);
            }
        }



