package com.demo.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greeting_noParam_returnsDefaultMessage() throws Exception {
        String defaultMessage = "Hello, World!";

        ResultActions resultActions = this.mockMvc.perform(get("/greeting"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").value(defaultMessage));
    }

    @Test
    public void greeting_withParam_shouldReturnTailoredMessage() throws Exception {
        String nameParameter = "Spring Community";
        String tailoredMessage = "Hello, " + nameParameter + "!";

        ResultActions resultActions = this.mockMvc.perform(get("/greeting")
                .param("name", nameParameter));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").value(tailoredMessage));
    }
}
