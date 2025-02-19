import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class CountRowsInCSVTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/basic/destination.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.writeNext(new String[]{"ID", "Name", "Age"});
            writer.writeNext(new String[]{"1", "John Doe", "30"});
            writer.writeNext(new String[]{"2", "Jane Smith", "25"});
            writer.writeNext(new String[]{"3", "Mark Taylor", "35"});
        }
    }

    @Test
    void testCountRows() {
        int count = 0;
        try (CSVReader reader = new CSVReader(new FileReader(TEST_CSV_FILE))) {
            count = reader.readAll().size(); // Count rows
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(4, count, "Row count should match the number of lines in the CSV file (including header).");
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

