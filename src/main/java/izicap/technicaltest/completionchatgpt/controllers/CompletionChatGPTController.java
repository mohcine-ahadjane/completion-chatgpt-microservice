package izicap.technicaltest.completionchatgpt.controllers;

import io.swagger.annotations.Api;
import izicap.technicaltest.completionchatgpt.services.CompletionOpenAIService;
import izicap.technicaltest.completionchatgpt.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "Completion API", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompletionChatGPTController {
    @Autowired
    private CompletionOpenAIService completionOpenAIService;

    @Autowired
    private CsvService csvService;

    @PostMapping("/answer")
    public String getAnswer(@RequestBody String question) throws Exception {
        // Call openai and obtain answer
        String answer = completionOpenAIService.getResponse(question);

        // Append question and answer to CSV file

        csvService.appendQuestionAnswer(question, answer);


        return answer;
    }
}
