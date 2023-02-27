package izicap.technicaltest.completionchatgpt.services;


import izicap.technicaltest.completionchatgpt.config.RequestConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class CsvService {

    private final char csvSeparator = ';';

    public void appendQuestionAnswer(String question, String answer){

        try {
            // Open the file for reading and writing
            RandomAccessFile raf = new RandomAccessFile(RequestConfig.CSV_FILE_PATH, "rw");

            // Read the first line of the file
            String firstLine = raf.readLine();


            // Check if the first line is equal to question;answer
            if (firstLine==null || !firstLine.equals("question;answer")) {
                // Update the first line with your desired string
                raf.seek(0);  // Move the file pointer to the beginning of the file
                //remove \n
                raf.writeBytes("question;answer\n");
            }
            raf.seek(raf.length());
            String record = question + csvSeparator + answer;
            raf.writeBytes(record.replace("\n", ""));
            raf.writeBytes("\n");

            // Close the file
            raf.close();


        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
