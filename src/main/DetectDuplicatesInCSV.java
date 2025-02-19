package com.week5.day1csv;


import java.io.*;
import java.util.*;

public class DetectDuplicatesInCSV {
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/advanced/data.csv";
        Map<String, List<String>> records = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header
                }

                String[] fields = line.split(",");
                String id = fields[0];

                if (records.containsKey(id)) {
                    duplicates.add(id);
                }
                records.put(id, Arrays.asList(fields));
            }

            System.out.println("Duplicate Records:");
            for (String id : duplicates) {
                System.out.println(String.join(",", records.get(id)));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

