package com.mint.CardVerification.controller;

import com.google.gson.Gson;
import com.mint.CardVerification.dto.CardVerifyResponse;
import com.mint.CardVerification.dto.CounterResponse;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class CardSchemeControllerTests extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void getCardinformationTest()  throws Exception {
        String uri = "/card-scheme/verify/45717360";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        CardVerifyResponse resp = gson.fromJson(content,CardVerifyResponse.class);
        assertTrue(resp!=null);
    }

    @Test
    public void getHitCountTest()  throws Exception {
        String uri = "/card-scheme/stats?start=1&limit=2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        CounterResponse resp = gson.fromJson(content,CounterResponse.class);
        assertTrue(resp!=null);
    }
}
