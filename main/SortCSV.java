package com.week5.day1csv;

import java.io.*;
import java.util.*;
import com.opencsv.*;  // OpenCSV library for reading CSV files

public class SortCSV {
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/intermediate/employees.csv";
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            boolean isHeader = true;
            String[] header = null;

            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    header = nextLine; // Store header separately
                    isHeader = false;
                    continue;
                }

                if (nextLine.length > 2) {
                    try {
                        Double.parseDouble(nextLine[2]); // Validate salary format
                        data.add(nextLine);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid salary entry: " + Arrays.toString(nextLine));
                    }
                }
            }

            // Sort by salary in descending order
            data.sort((a, b) -> Double.compare(Double.parseDouble(b[2]), Double.parseDouble(a[2])));

            System.out.println("Top 5 Highest-Paid Employees:");
            System.out.println(String.join(", ", header)); // Print header
            for (int i = 0; i < Math.min(5, data.size()); i++) {
                System.out.println(String.join(", ", data.get(i)));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

