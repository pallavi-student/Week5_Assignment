import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

class ReadLargeCsvFileTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/advanced/largefile.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("ID,Name,Age,Department,Salary");
            writer.println("1,John Doe,30,IT,50000");
            writer.println("2,Jane Smith,28,HR,55000");
            writer.println("3,Mark Taylor,35,Finance,60000");
        }
    }

    @Test
    void testReadLargeCSV() throws Exception {
        int batchSize = 2;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            List<String> batch = new ArrayList<>();
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                batch.add(line);
                if (batch.size() == batchSize) {
                    count += batch.size();
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                count += batch.size();
            }
        }

        assertEquals(3, count);
    }
}
