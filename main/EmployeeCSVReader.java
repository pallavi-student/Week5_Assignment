package com.week5.day1csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeCSVReader {
    public static void main(String[] args) {
        String fileName = "src/main/resources/source.csv";
        int recordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                recordCount++;
            }

            System.out.println("Total records excluding header: " + recordCount);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
