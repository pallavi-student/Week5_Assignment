package com.week5.day1csv;

import java.io.*;
import java.util.*;

public class CSVDuplicates {
    public static void findDuplicates(String filePath) throws IOException {
        Map<String, String> records = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                if (records.containsKey(id)) {
                    duplicates.add(line);
                } else {
                    records.put(id, line);
                }
            }
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            System.out.println("Duplicate Records:");
            duplicates.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        String filePath = "large_employees.csv";
        try {
            findDuplicates(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
