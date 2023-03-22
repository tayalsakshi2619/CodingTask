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
    public void itShouldReturnList_Status200_1() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=1","10"));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnList_Status200_2() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=2","10").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnList_Status200_3() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}","10").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturn_Status400_1() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=1"," 12 3").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturn_Status400_2() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=2","%76$"));
        getRequestResult.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturn_Status404_1() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=1","1").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturn_Status404_2() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=2","-10"));
        getRequestResult.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturn_Status404_3() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=1", "0").contentType(MediaType.APPLICATION_JSON));
        getRequestResult.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void itShouldReturnXmlResponse() throws Exception {
        ResultActions getRequestResult = mockMvc.perform(get("/primes/{num}?version=1","13").accept(MediaType.APPLICATION_XML));
        getRequestResult.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
