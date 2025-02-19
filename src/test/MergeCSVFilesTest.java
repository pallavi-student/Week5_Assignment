import static org.junit.jupiter.api.Assertions.*;

import com.week5.day1csv.MergeCSVFiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

class MergeCSVFilesTest {
    private static final String TEST_FILE1 = "src/main/java/com/day1/advanced/student1.csv";
    private static final String TEST_FILE2 = "src/main/java/com/day1/advanced/student2.csv";
    private static final String OUTPUT_FILE = "src/main/java/com/day1/advanced/mergefiles.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create test file 1
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE1))) {
            writer.println("ID,Name,Age");
            writer.println("1,John Doe,20");
            writer.println("2,Jane Smith,22");
        }

        // Create test file 2
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE2))) {
            writer.println("ID,Marks,Grade");
            writer.println("1,85,A");
            writer.println("2,90,A+");
        }
    }

    @Test
    void testMergeCSVFiles() throws Exception {
        MergeCSVFiles.main(new String[]{});

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        // Verify file content
        assertEquals(3, lines.size()); // Header + 2 data rows
        assertEquals("ID,Name,Age,Marks,Grade", lines.get(0));
        assertTrue(lines.contains("1,John Doe,20,85,A"));
        assertTrue(lines.contains("2,Jane Smith,22,90,A+"));
    }
}

