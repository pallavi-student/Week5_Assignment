package com.day2json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ValidateEmail {
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"Bheem\", \"email\":\"bheem@gmail.com\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        String email = jsonObject.get("email").getAsString();
        if (isValidEmail(email)) {
            System.out.println("Valid email: " + email);
        } else {
            System.out.println("Invalid email format");
        }
    }
}

