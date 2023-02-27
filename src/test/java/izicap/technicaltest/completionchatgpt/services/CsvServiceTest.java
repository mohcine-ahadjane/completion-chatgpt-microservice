package izicap.technicaltest.completionchatgpt.services;

import izicap.technicaltest.completionchatgpt.config.RequestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CsvServiceTest {
    private CsvService csvService;

    @BeforeEach
    public void setup() {
        csvService = new CsvService();
    }

    @Test
    public void testAppendQuestionAnswer() throws IOException {
        String question = "What is your name?";
        String answer = "My name is ChatGPT.";

        // Set up the test by creating a temporary file
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();
//        String tempFile.getPath() = RequestConfig.CSV_FILE_PATH ;

        // Call the method being tested
        csvService.appendQuestionAnswer(question, answer);

        // Verify that the file was updated correctly
        String[] lines = Files.readAllLines(Paths.get(RequestConfig.CSV_FILE_PATH)).toArray(new String[0]);
        assertEquals("question;answer", lines[0]);
        assertEquals(question + ";" + answer, lines[1]);
    }

    @Test
    public void testAppendQuestionAnswerWithNewline() throws IOException {
        String question = "What is your favorite food?";
        String answer = "My favorite food is pizza.";

        // Set up the test by creating a temporary file
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();

        // Call the method being tested
        csvService.appendQuestionAnswer(question, answer);

        // Verify that the file was updated correctly
        String[] lines = Files.readAllLines(Paths.get(RequestConfig.CSV_FILE_PATH)).toArray(new String[0]);
        assertEquals("question;answer", lines[0]);
        assertEquals("What is your favorite food?;My favorite food is pizza.", lines[lines.length-1]);
    }

    @Test
    public void testAppendQuestionAnswerWithoutCsvHeader() throws IOException {
        String question = "What is your name?";
        String answer = "My name is ChatGPT.";

        // Set up the test by creating a temporary file without CSV header
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();
        Files.write(Paths.get(RequestConfig.CSV_FILE_PATH), "header line\n".getBytes());

        // Call the method being tested
        csvService.appendQuestionAnswer(question, answer);

        // Verify that the file was updated correctly
        String[] lines = Files.readAllLines(Paths.get(RequestConfig.CSV_FILE_PATH)).toArray(new String[0]);
        assertTrue(lines[0].startsWith("question;answer"));
        assertEquals(question + ";" + answer, lines[1]);
    }
}
