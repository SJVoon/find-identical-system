package program.identifier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import program.Program;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LegacyCustomerIdentifierProgramTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testRunProgram() throws IOException {
        String content = "001,James Bond,IC,007,1920/02/28\n" +
                "002,Ian Flaming,IC,007,1920/02/28\n" +
                "003,James Bond,IC,777,1990/12/30";
        File testFile = createTestFile(content);

        Program program = new LegacyCustomerIdentifierProgram();
        program.runProgram("test.csv");

        assertTrue("A new pair of customer found, ID for Customers that are matched : 001 and 002"
                .equals(outputStreamCaptor.toString().trim())
                || "A new pair of customer found, ID for Customers that are matched : 002 and 001"
                .equals(outputStreamCaptor.toString().trim()));

        deleteTestFile(testFile);
    }

    private File createTestFile(String content) throws IOException {
        File testFile = new File("test.csv");
        Files.writeString(testFile.toPath(), content);
        return testFile;
    }

    private void deleteTestFile(File testFile) {
        Path filePath = testFile.toPath();
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}