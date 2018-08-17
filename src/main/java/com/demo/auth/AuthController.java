package com.demo.auth;

import com.demo.auth.dto.AuthAnswerDto;
import com.demo.auth.dto.AuthRequestDto;
import com.demo.auth.dto.AuthResponseDto;
import com.demo.util.JsonUtils;
import com.demo.util.ServerEndpoints;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.demo.util.JsonUtils.removeFintechEnvelope;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthController {

    static final String RECEIVE_ENDPOINT = "/auth";
    static final String SEND_ENDPOINT = "/authSend";
    static final String TEST_ENDPOINT = "/authTest";

    @Autowired
    private ServerEndpoints config;

    @RequestMapping(value = RECEIVE_ENDPOINT, method = POST)
    public ResponseEntity<String> receiveAuth(@RequestBody String jsonString) {
        JSONObject fintechJson = new JSONObject(jsonString);
        Gson gson = new Gson();
        JSONObject responseJson = removeFintechEnvelope(fintechJson);
        AuthResponseDto auth = JsonUtils.fromJson(extractAuthData(responseJson).toString(), AuthResponseDto.class);
        AuthAnswerDto answer = JsonUtils.fromJson(extractAuthAnswer(responseJson).toString(), AuthAnswerDto.class);

        System.out.println("Received Auth -> " + gson.toJson(auth));
        System.out.println("Received Auth Answer -> " + gson.toJson(answer));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private JSONObject extractAuthData(JSONObject responseJson) {
        return responseJson.getJSONObject("data");
    }

    private JSONObject extractAuthAnswer(JSONObject responseJson) {
        return responseJson.getJSONObject("answer").getJSONObject("auth_attributes");
    }

    @RequestMapping(value = SEND_ENDPOINT, method = POST)
    public ResponseEntity<String> sendAuth(@RequestBody String authString) {
        AuthRequestDto auth = JsonUtils.fromJson(authString, AuthRequestDto.class);
        System.out.println("Sending auth to : " + auth.subjectId);
        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String myServerEndpoint = "http://172.30.1.75:8080/"+ RECEIVE_ENDPOINT;
        String requestBody = auth.buildFintechJson(myServerEndpoint).toString();
        HttpEntity<String> request = new HttpEntity<>(requestBody);

        String response = restTemplate.postForObject(ServerEndpoints.getServerAuthEndpoint(), request, String.class);
        System.out.println("Response -> " + response);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @RequestMapping(value = TEST_ENDPOINT, method = POST)
    public ResponseEntity<String> testAuthFromServer(@RequestBody String jsonString) {
        System.out.println("Received auth test -> " + jsonString);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
