package com.demo.tdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.demo.tdata.TDataController.RECEIVE_ENDPOINT;
import static com.demo.tdata.TDataController.SEND_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TDataControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void receiveTData_validRequest_returnsStatusOk() throws Exception {
        String receivedTData = "{\"fintech\":{\"emcert\":\"60028768\",\"payload\":\"test tdata message\"}}";

        ResultActions resultActions = this.mockMvc.perform(post(RECEIVE_ENDPOINT)
                .content(receivedTData)
                .contentType(APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void sendAuth_validRequest_returnsStatusOk() throws Exception {
        String tDataToSend = "{\"emcert\":\"60028565\",\"payload\":\"Test tData message\"}";

        ResultActions resultActions = this.mockMvc.perform(post(SEND_ENDPOINT)
                .content(tDataToSend)
                .contentType(APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk());
    }

}
