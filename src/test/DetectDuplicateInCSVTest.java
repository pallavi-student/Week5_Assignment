import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

class DetectDuplicatesInCSVTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/advanced/data.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("ID,Name,Age,Department,Salary");
            writer.println("1,John Doe,30,IT,50000");
            writer.println("2,Jane Smith,28,HR,55000");
            writer.println("3,Mark Taylor,35,Finance,60000");
            writer.println("1,John Doe,30,IT,50000");
            writer.println("4,Emily Davis,26,Marketing,52000");
            writer.println("2,Jane Smith,28,HR,55000");
        }
    }

    @Test
    void testDetectDuplicates() throws Exception {
        Map<String, Integer> recordCount = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header
                }

                String[] fields = line.split(",");
                String id = fields[0];

                recordCount.put(id, recordCount.getOrDefault(id, 0) + 1);
                if (recordCount.get(id) > 1) {
                    duplicates.add(id);
                }
            }
        }

        assertTrue(duplicates.contains("1"));
        assertTrue(duplicates.contains("2"));
        assertEquals(2, duplicates.size());
    }
}

