package com.week5.day1csv;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerateCSVReportFromDatabase {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql///employee";
        String dbUser = "root";
        String dbPassword = "Garima@123";
        String csvFile = "src/main/java/com/day1/advanced/employee+_report.csv";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             FileWriter writer = new FileWriter(csvFile)) {

            String query = "SELECT employee_id, name, department, salary FROM employees";
            ResultSet resultSet = statement.executeQuery(query);

            // Write headers
            writer.append("Employee ID,Name,Department,Salary\n");

            // Write data rows
            while (resultSet.next()) {
                writer.append(resultSet.getInt("employee_id") + ",")
                        .append(resultSet.getString("name") + ",")
                        .append(resultSet.getString("department") + ",")
                        .append(resultSet.getDouble("salary") + "\n");
            }

            System.out.println("CSV report generated successfully: " + csvFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

