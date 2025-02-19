package com.week5.day1csv;

import java.io.*;
import java.util.*;
import com.opencsv.*;  // OpenCSV library for reading CSV files

public class ModifyCSVFile {
    public static void main(String[] args) {
        String inputCsvFile = "src/main/java/com/day1/intermediate/employees.csv";
        String outputCsvFile = "src/main/java/com/day1/intermediate/updatedemployee.csv";

        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputCsvFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputCsvFile))) {

            String[] nextLine;
            boolean headerSkipped = false;

            while ((nextLine = reader.readNext()) != null) {
                if (!headerSkipped) {
                    data.add(nextLine); // Add header as is
                    headerSkipped = true;
                    continue;
                }

                if (nextLine[1].equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(nextLine[2]);
                    salary *= 1.10; // Increase salary by 10%
                    nextLine[2] = String.format("%.2f", salary);
                }

                data.add(nextLine);
            }

            writer.writeAll(data);
            System.out.println("Updated salaries saved to " + outputCsvFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

