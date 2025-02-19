import static org.junit.jupiter.api.Assertions.*;

import com.week5.day1csv.ValidateCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;

class ValidateCSVTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/intermediate/employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("Name,Department,Salary,Email,Phone");
            writer.println("John Doe,IT,60000,john.doe@example.com,9876543210");
            writer.println("Jane Smith,HR,55000,jane.smith@company,12345"); // Invalid email & phone
            writer.println("Mark Taylor,Finance,70000,mark.taylor@email.com,1234567890");
            writer.println("Alice Brown,Marketing,80000,alice.brown@company.com,0987654321");
            writer.println("Bob White,IT,75000,bob.white@website,98765432"); // Invalid phone
        }
    }

    @Test
    void testValidateCSVData() throws Exception {
        ValidateCSV.main(new String[]{});

        List<String[]> validData = new ArrayList<>();
        List<String[]> invalidData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header
                }

                String[] row = line.split(",");
                if (row[3].matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && row[4].matches("^\\d{10}$")) {
                    validData.add(row);
                } else {
                    invalidData.add(row);
                }
            }
        }

        // Verify valid data count
        assertEquals(3, validData.size()); // 3 valid rows

        // Verify invalid data count
        assertEquals(2, invalidData.size()); // 2 invalid rows
    }
}

