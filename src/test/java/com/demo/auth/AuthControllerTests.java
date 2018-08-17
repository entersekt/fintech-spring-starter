package com.demo.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.demo.auth.AuthController.RECEIVE_ENDPOINT;
import static com.demo.auth.AuthController.SEND_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void receiveAuth_validRequest_returnsStatusOk() throws Exception {
        String receivedAuth = "{\"fintech\":{\"data\":{\"auth_type\":\"auth\",\"auth_id\":\"5cdc2176-269b-45fd-90a2-14e2eb989191\",\"subject_id\":\"60028768\",\"timestamp\":1534459592613,\"auth_attributes\":{\"title\":\"test auth\",\"text\":\"Bla bla\",\"buttons\":[{\"label\":\"Yes\",\"role\":\"positive\",\"flags\":[]},{\"label\":\"No\",\"role\":\"negative\",\"flags\":[]}],\"text_boxes\":[{\"label\":\"Name\",\"min_size\":0,\"max_size\":0,\"constraints\":0},{\"label\":\"Surname\",\"min_size\":0,\"max_size\":0,\"constraints\":0}],\"value_pairs\":[]}},\"answer\":{\"auth_attributes\":{\"subject_reply\":\"No\",\"text_fields\":[\"text_entered\",\"\"]}}}}";

        ResultActions resultActions = this.mockMvc.perform(post(RECEIVE_ENDPOINT)
                .content(receivedAuth)
                .contentType(APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void sendAuth_validRequest_returnsStatusOk() throws Exception {
        String authToSend = "{\"subject_id\":\"60028565\",\"attributes\":{\"push_notify\":true,\"title\":\"SomeTitle\",\"text\":\"Sometext\"}}";

        ResultActions resultActions = this.mockMvc.perform(post(SEND_ENDPOINT)
                .content(authToSend)
                .contentType(APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk());
    }

}
