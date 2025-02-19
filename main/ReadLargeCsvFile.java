package com.week5.day1csv;


import java.io.*;
import java.util.*;

public class ReadLargeCsvFile{
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/advanced/largefile.csv";
        int batchSize = 100;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            List<String> batch = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                batch.add(line);
                if (batch.size() == batchSize) {
                    processBatch(batch);
                    count += batch.size();
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                processBatch(batch);
                count += batch.size();
            }

            System.out.println("Total records processed: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processBatch(List<String> batch) {
        System.out.println("Processing " + batch.size() + " records...");
    }
}

