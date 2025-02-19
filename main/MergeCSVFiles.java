package com.week5.day1csv;

import java.io.*;
import java.util.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class MergeCSVFiles {
    public static void main(String[] args) {
        String file1 = "src/main/java/com/day1/advanced/student1.csv";
        String file2 = "src/main/java/com/day1/advanced/student2.csv";
        String outputFile = "src/main/java/com/day1/advanced/mergefiles.csv";

        Map<String, String[]> studentData = new HashMap<>();

        try (CSVReader reader1 = new CSVReader(new FileReader(file1))) {
            String[] nextLine;
            boolean isHeader = true;

            while ((nextLine = reader1.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                studentData.put(nextLine[0], new String[]{nextLine[1], nextLine[2]});
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + file1);
        }

        try (CSVReader reader2 = new CSVReader(new FileReader(file2))) {
            String[] nextLine;
            boolean isHeader = true;

            while ((nextLine = reader2.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (studentData.containsKey(nextLine[0])) {
                    String[] details = studentData.get(nextLine[0]);
                    studentData.put(nextLine[0], new String[]{details[0], details[1], nextLine[1], nextLine[2]});
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file2);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"});
            for (Map.Entry<String, String[]> entry : studentData.entrySet()) {
                String[] row = new String[5];
                row[0] = entry.getKey();
                System.arraycopy(entry.getValue(), 0, row, 1, entry.getValue().length);
                writer.writeNext(row);
            }
            System.out.println("Merged file created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFile);
        }
    }
}

