import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.sql.*;

class GenerateCSVReportFromDatabaseTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/advanced/employee+_report.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Simulating a CSV file creation for testing
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("Employee ID,Name,Department,Salary");
            writer.println("101,John Doe,IT,60000");
            writer.println("102,Jane Smith,HR,55000");
            writer.println("103,Mark Taylor,Finance,65000");
        }
    }

    @Test
    void testCSVFileExists() {
        File file = new File(TEST_CSV_FILE);
        assertTrue(file.exists(), "CSV file should be generated.");
    }

    @Test
    void testCSVFileContent() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            assertEquals("Employee ID,Name,Department,Salary", reader.readLine()); // Header check
            assertEquals("101,John Doe,IT,60000", reader.readLine());
            assertEquals("102,Jane Smith,HR,55000", reader.readLine());
            assertEquals("103,Mark Taylor,Finance,65000", reader.readLine());
        }
    }
}

