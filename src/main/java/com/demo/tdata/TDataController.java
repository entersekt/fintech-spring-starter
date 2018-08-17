package com.demo.tdata;

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
public class TDataController {

    static final String RECEIVE_ENDPOINT = "/tData";
    static final String SEND_ENDPOINT = "/tDataSend";
    static final String TEST_ENDPOINT = "/tDataTest";

    @Autowired
    private ServerEndpoints config;

    @RequestMapping(value = RECEIVE_ENDPOINT, method = POST)
    public ResponseEntity<String> receiveTData(@RequestBody String jsonString) {
        JSONObject fintechJson = new JSONObject(jsonString);
        Gson gson = new Gson();
        TDataDto tData = gson.fromJson(removeFintechEnvelope(fintechJson).toString(), TDataDto.class);
        System.out.println("Received tData -> " + gson.toJson(tData));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = SEND_ENDPOINT, method = POST)
    public ResponseEntity<String> sendTData(@RequestBody TDataDto tData) {
        String requestBody = tData.buildFintechJson().toString();
        HttpEntity<String> request = new HttpEntity<>(requestBody);

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String response = restTemplate.postForObject(ServerEndpoints.getServerTDataEndpoint(), request, String.class);
        System.out.println("Response -> " + response);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = TEST_ENDPOINT, method = POST)
    public ResponseEntity<String> testTDataFromServer(@RequestBody String jsonString) {
        System.out.println("JSON Received -> " + jsonString);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
