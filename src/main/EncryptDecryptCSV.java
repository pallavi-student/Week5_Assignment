package com.week5.day1csv;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class EncryptDecryptCSV {
    private static final String ALGORITHM = "AES";
    private static SecretKey secretKey;

    static {
        try {
            // Generate a fixed key to ensure encryption & decryption work properly
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            secretKey = keyGen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    public static void encryptAndWriteCSV(String inputFile, String outputFile) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            String line = reader.readLine();
            writer.println(line); // Write headers as is

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) { // Ensure correct structure
                    fields[2] = encrypt(fields[2]); // Encrypt Salary
                    fields[3] = encrypt(fields[3]); // Encrypt Email
                    writer.println(String.join(",", fields));
                }
            }
        }
    }

    public static void decryptAndReadCSV(String inputFile) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            System.out.println(line); // Print header

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    fields[2] = decrypt(fields[2]); // Decrypt Salary
                    fields[3] = decrypt(fields[3]); // Decrypt Email
                    System.out.println(String.join(",", fields));
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            encryptAndWriteCSV("src/main/java/com/day1/advanced/encrypt.csv", "src/main/java/com/day1/advanced/decrypt.csv");
            System.out.println("Encrypted CSV file created successfully.");

            System.out.println("\nDecrypted CSV content:");
            decryptAndReadCSV("src/main/java/com/day1/advanced/encrypt.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
