package izicap.technicaltest.completionchatgpt.services;

import izicap.technicaltest.completionchatgpt.config.RequestConfig;
import izicap.technicaltest.completionchatgpt.model.request.Request;
import izicap.technicaltest.completionchatgpt.model.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class CompletionOpenAIServiceTest {
    private CompletionOpenAIService service = new CompletionOpenAIService();

    @Test
    public void testGetResponseBody() {
        Request request = new Request("davinci", "Hello", 0.5, 5, 0.9);
        HttpEntity<Request> httpEntity = service.buildHttpEntity(request);

        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.postForObject(
                RequestConfig.OPENAI_API_URL,
                httpEntity,
                Response.class);

        Assertions.assertNotNull(response);
    }

    @Test
    public void testGetResponse() {
        String prompt = "What is the capital of France?";
        String expectedResponse = "Paris";

        String response = service.getResponse(prompt);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains(expectedResponse));
    }
}
