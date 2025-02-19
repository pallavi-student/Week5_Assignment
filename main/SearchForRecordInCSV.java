package com.week5.day1csv;

import java.io.*;
import java.util.*;
import com.opencsv.*;  // OpenCSV library for reading CSV files

public class SearchForRecordInCSV {
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/intermediate/employees.csv";
        String searchName;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the employee name to search: ");
        searchName = scanner.nextLine();
        scanner.close();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            boolean found = false;

            while ((nextLine = reader.readNext()) != null) {
                // Assuming CSV format: Name,Department,Salary
                if (nextLine[0].equalsIgnoreCase(searchName)) {
                    System.out.println("Employee Found!");
                    System.out.println("Department: " + nextLine[1]);
                    System.out.println("Salary: " + nextLine[2]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

