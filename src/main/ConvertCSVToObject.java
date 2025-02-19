package com.week5.day1csv;


import java.io.*;
import java.util.*;
import com.opencsv.*;  // OpenCSV library for reading CSV files

class Student {
    private String name;
    private int age;
    private String email;

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

public class ConvertCSVToObject {
    public static void main(String[] args) {
        String csvFile = "src/main/java/com/day1/intermediate/student.csv";
        List<Student> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            boolean isHeader = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header row
                    continue;
                }

                if (nextLine.length >= 3) {
                    try {
                        String name = nextLine[0];
                        int age = Integer.parseInt(nextLine[1]);
                        String email = nextLine[2];
                        students.add(new Student(name, age, email));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid entry: " + Arrays.toString(nextLine));
                    }
                }
            }

            System.out.println("Students List:");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

