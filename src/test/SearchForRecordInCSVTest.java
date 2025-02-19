import static org.junit.jupiter.api.Assertions.*;

import com.week5.day1csv.SearchForRecordInCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;

class SearchForRecordInCSVTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/intermediate/employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("Name,Department,Salary");
            writer.println("John Doe,IT,60000");
            writer.println("Jane Smith,HR,55000");
            writer.println("Mark Taylor,Finance,70000");
        }
    }

    @Test
    void testSearchEmployeeFound() throws Exception {
        // Redirect System.in for testing
        ByteArrayInputStream in = new ByteArrayInputStream("John Doe\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SearchForRecordInCSV.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Employee Found!"));
        assertTrue(output.contains("Department: IT"));
        assertTrue(output.contains("Salary: 60000"));
    }

    @Test
    void testSearchEmployeeNotFound() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("Alice Brown\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SearchForRecordInCSV.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Employee not found."));
    }
}

