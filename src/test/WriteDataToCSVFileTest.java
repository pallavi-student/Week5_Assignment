import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

public class WriteDataToCSVFileTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/basic/destination.csv";

    @Test
    void testWriteDataToCSVFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_CSV_FILE))) {
            bw.write("Id,Name,Department,Salary\n");
            bw.write("111,Garima,Engineering,60000\n");
            bw.write("121,Raj,Finance,60000\n");
            bw.write("131,Rani,Teaching,60000\n");
            bw.write("142,Sam,HR,60000\n");
            bw.write("145,John,Engineering,60000\n");
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }

        // Verify that the file exists
        assertTrue(Files.exists(Paths.get(TEST_CSV_FILE)), "CSV file should be created.");

        // Read and verify contents
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            fail("Exception occurred while reading the file: " + e.getMessage());
        }

        // Check number of lines
        assertEquals(6, lines.size(), "CSV should contain 6 lines (including header).");

        // Validate header and first data row
        assertEquals("Id,Name,Department,Salary", lines.get(0), "Header should match.");
        assertEquals("111,Garima,Engineering,60000", lines.get(1), "First row should match.");
        assertEquals("145,John,Engineering,60000", lines.get(5), "Last row should match.");
    }

    @AfterEach
    void tearDown() {
        // Clean up test file
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}

