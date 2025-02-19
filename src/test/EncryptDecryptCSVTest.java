import com.week5.day1csv.EncryptDecryptCSV;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptDecryptCSVTest {

    private static final String TEST_INPUT_CSV = "src/main/java/com/day1/advanced/employee+_report.csv";
    private static final String TEST_ENCRYPTED_CSV = "src/main/java/com/day1/advanced/encrypt.csv";

    @Test
    void testEncryptionDecryption() throws Exception {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_INPUT_CSV))) {
            writer.println("Employee ID,Name,Salary,Email");
            writer.println("101,John Doe,60000,john@example.com");
            writer.println("102,Jane Smith,55000,jane@example.com");
        }

        // Encrypt the CSV file
        EncryptDecryptCSV.encryptAndWriteCSV(TEST_INPUT_CSV, TEST_ENCRYPTED_CSV);
        assertTrue(new File(TEST_ENCRYPTED_CSV).exists(), "Encrypted CSV file should be created.");

        // Read and decrypt the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_ENCRYPTED_CSV))) {
            String header = reader.readLine(); // Read header
            assertEquals("Employee ID,Name,Salary,Email", header, "Header should remain unchanged.");

            String encryptedLine = reader.readLine();
            assertNotNull(encryptedLine, "Encrypted data should exist.");
            String[] encryptedFields = encryptedLine.split(",");

            // Ensure the encrypted values are not the same as original
            assertNotEquals("60000", encryptedFields[2], "Salary should be encrypted.");
            assertNotEquals("john@example.com", encryptedFields[3], "Email should be encrypted.");
        }

        // Decrypt and verify data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to capture output
        EncryptDecryptCSV.decryptAndReadCSV(TEST_ENCRYPTED_CSV);
        String consoleOutput = outputStream.toString();

        assertTrue(consoleOutput.contains("101,John Doe,60000,john@example.com"), "Decrypted data should match original.");
        assertTrue(consoleOutput.contains("102,Jane Smith,55000,jane@example.com"), "Decrypted data should match original.");
    }
}

