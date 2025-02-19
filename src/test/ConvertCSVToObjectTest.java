import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;

class ConvertCSVToObjectTest {
    private static final String TEST_CSV_FILE = "src/main/java/com/day1/intermediate/student.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.println("Name,Age,Email");
            writer.println("John Doe,20,john.doe@example.com");
            writer.println("Jane Smith,22,jane.smith@example.com");
            writer.println("Alice Brown,19,alice.brown@example.com");
        }
    }

    @Test
    void testCSVToStudentObjects() throws Exception {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header
                }

                String[] row = line.split(",");
                if (row.length >= 3) {
                    String name = row[0];
                    int age = Integer.parseInt(row[1]);
                    String email = row[2];
                    students.add(new Student(name, age, email));
                }
            }
        }

        // Verify student list size
        assertEquals(3, students.size());

        // Verify student details
        assertEquals("John Doe", students.get(0).toString().contains("John Doe"));
        assertEquals("Jane Smith", students.get(1).toString().contains("Jane Smith"));
        assertEquals("Alice Brown", students.get(2).toString().contains("Alice Brown"));
    }
}

