package com.task.rest.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldReturnListV1_Status200() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/v1/{num}","10"));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnListV2_Status200() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/v2/{num}","10").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnV1_Status400() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/v1/{num}"," 12 3").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnV2_Status400() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/v2/{num}","%76$"));
        getRequestResult.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnXmlResponse_V1() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/v1/{num}","13").accept(MediaType.APPLICATION_XML));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
