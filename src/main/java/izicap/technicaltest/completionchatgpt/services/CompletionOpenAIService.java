package izicap.technicaltest.completionchatgpt.services;


import izicap.technicaltest.completionchatgpt.config.RequestConfig;
import izicap.technicaltest.completionchatgpt.model.request.Request;
import izicap.technicaltest.completionchatgpt.model.response.Choice;
import izicap.technicaltest.completionchatgpt.model.response.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CompletionOpenAIService {

    private static RestTemplate restTemplate = new RestTemplate();

    //    Build headers
    public HttpEntity<Request> buildHttpEntity(Request request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(RequestConfig.MEDIA_TYPE));
        headers.add(RequestConfig.AUTHORIZATION, RequestConfig.BEARER + RequestConfig.API_KEY);
        return new HttpEntity<>(request, headers);
    }

    //    Generate response
    public Response getResponseBody(HttpEntity<Request> requestHttpEntity) {
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(
                RequestConfig.OPENAI_API_URL,
                requestHttpEntity,
                Response.class);

        return responseEntity.getBody();
    }



    public String getResponse(String prompt) {
        Response response= this.getResponseBody(
                this.buildHttpEntity(
                        new Request(
                                RequestConfig.MODEL,
                                prompt,
                                RequestConfig.TEMPERATURE,
                                RequestConfig.MAX_TOKEN,
                                RequestConfig.TOP_P)));
        List<Choice> choices =  response.getChoices();
        return choices.get(0).getText();

    }

}
