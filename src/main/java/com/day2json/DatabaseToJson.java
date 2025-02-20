package com.day2json;

import com.google.gson.*;
import java.sql.*;

public class DatabaseToJson {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "Pallavi@123");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        JsonArray jsonArray = new JsonArray();
        while (rs.next()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", rs.getInt("id"));
            json.addProperty("name", rs.getString("name"));
            json.addProperty("age", rs.getInt("age"));
            jsonArray.add(json);
        }

        conn.close();
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(jsonArray));
    }
}

