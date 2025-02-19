package com.week5.day1csv;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.opencsv.*;  // OpenCSV library for reading CSV files

public class ValidateCSV {
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/intermediate/employees.csv";
        List<String[]> validData = new ArrayList<>();
        List<String[]> invalidData = new ArrayList<>();

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phoneRegex = "^\\d{10}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            boolean isHeader = true;
            String[] header = null;

            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    header = nextLine; // Store header separately
                    isHeader = false;
                    validData.add(header); // Keep header in valid data
                    continue;
                }

                if (nextLine.length > 3) {
                    Matcher emailMatcher = emailPattern.matcher(nextLine[3]);
                    Matcher phoneMatcher = phonePattern.matcher(nextLine[4]);

                    if (emailMatcher.matches() && phoneMatcher.matches()) {
                        validData.add(nextLine);
                    } else {
                        invalidData.add(nextLine);
                    }
                } else {
                    invalidData.add(nextLine);
                }
            }

            System.out.println("Valid Data:");
            for (String[] row : validData) {
                System.out.println(String.join(", ", row));
            }

            System.out.println("\nInvalid Data:");
            for (String[] row : invalidData) {
                System.out.println("Invalid Entry: " + String.join(", ", row));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

