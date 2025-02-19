import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

public class ReadCSVFileTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/basic/source.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("Id,Name,Age,Marks");
            writer.println("1,John Doe,30,85");
            writer.println("2,Jane Smith,25,90");
            writer.println("3,Mark Taylor,35,78");
        }
    }

    @Test
    void testReadCSVFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

        // Validate file reading
        assertEquals(4, lines.size(), "CSV should contain 4 lines (including header).");
        assertEquals("Id,Name,Age,Marks", lines.get(0), "Header should match.");
        assertEquals("1,John Doe,30,85", lines.get(1), "First data row should match.");
        assertEquals("2,Jane Smith,25,90", lines.get(2), "Second data row should match.");
        assertEquals("3,Mark Taylor,35,78", lines.get(3), "Third data row should match.");
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

