import static org.junit.jupiter.api.Assertions.*;

import com.week5.day1csv.SortCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;

class SortCSVTest {
    private static final String INPUT_CSV_FILE = "src/main/java/com/day1/intermediate/employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(INPUT_CSV_FILE))) {
            writer.println("Name,Department,Salary");
            writer.println("John Doe,IT,60000");
            writer.println("Jane Smith,HR,55000");
            writer.println("Mark Taylor,Finance,70000");
            writer.println("Alice Brown,Marketing,80000");
            writer.println("Bob White,IT,75000");
        }
    }

    @Test
    void testSortEmployeesBySalary() throws Exception {
        SortCSV.main(new String[]{});

        List<String[]> sortedData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_CSV_FILE))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header
                }
                sortedData.add(line.split(","));
            }
        }

        // Check if employees are sorted in descending order of salary
        assertEquals("John Doe", sortedData.get(0)[0]); // Highest salary
        assertEquals("Jane Smith", sortedData.get(1)[0]);
        assertEquals("Mark Taylor", sortedData.get(2)[0]);
        assertEquals("John Doe", sortedData.get(3)[0]);
        assertEquals("Jane Smith", sortedData.get(4)[0]); // Lowest salary
    }
}

