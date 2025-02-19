import static org.junit.jupiter.api.Assertions .*;

import com.week5.day1csv.ModifyCSVFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io .*;
import java.util .*;

class ModifyCSVFileTest {
    private static final String INPUT_CSV_FILE = "src/main/java/com/day1/intermediate/employees.csv";
    private static final String OUTPUT_CSV_FILE = "src/main/java/com/day1/intermediate/updatedemployee.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(INPUT_CSV_FILE))) {
            writer.println("Name,Department,Salary");
            writer.println("John Doe,IT,60000");
            writer.println("Jane Smith,HR,55000");
            writer.println("Mark Taylor,Finance,70000");
        }
    }

    @Test
    void testUpdateEmployeeSalary() throws Exception {
        ModifyCSVFile.main(new String[]{});

        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        }

        assertEquals("Name", data.get(0)[0]); // Checking header
        assertEquals("66000.00", data.get(1)[2]); // 10% increase for IT department
        assertEquals("55000", data.get(2)[2]); // HR salary unchanged
        assertEquals("70000", data.get(3)[2]); // Finance salary unchanged
    }
}

