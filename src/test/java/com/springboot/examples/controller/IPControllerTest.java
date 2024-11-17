package com.springboot.examples.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.examples.model.IPInfo;
import com.springboot.examples.model.Result;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "env_var=test")
@AutoConfigureMockMvc
class IPControllerTest {
	
	@Autowired
    private MockMvc mvc;
    
    @Test
    void flowTest() throws Exception {

        // checks that at startup the value is not available
        mvc.perform(MockMvcRequestBuilders.get("/ip-addresses/{ip-value}","1.2.3.4"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{"
                		+ "\"success\":true,"
                		+ "\"message\":\"Retrieved 0 results\","
                		+ "\"data\":[]"
                		+ "}")));

        // adds a ip info
        mvc.perform(MockMvcRequestBuilders
                .post("/ip-address")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n"
                		+ "  \"type\": \"ipv4\",\r\n"
                		+ "  \"value\": \"1.2.3.4\",\r\n"
                		+ "  \"firstSeen\": \"2022-06-15T19:34:59.348\",\r\n"
                		+ "  \"totalCount\": 90\r\n"
                		+ "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Retrieves the last saved IP Address
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/ip-addresses/{ip-value}","1.2.3.4")).andReturn();
        String content = result.getResponse().getContentAsString();        
        ObjectMapper mapper = new ObjectMapper();
        Result<List<IPInfo>> value = mapper.readValue(content, Result.class);
		Assert.assertSame("Result should be success",true,value.isSuccess());
		Assert.assertSame("List Size should be 1",1,value.getData().size());
		
		// Negative Test Case to validate the input data
		result = mvc.perform(MockMvcRequestBuilders
                .post("/ip-address")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n"
                		+ "  \"type\": \"ipv4\",\r\n"
                		+ "  \"value\": \"1.2.3.4\",\r\n"
                		+ "  \"firstSeen\": \"2022-06-15T19:34:59.348Z\",\r\n"
                		+ "  \"totalCount\": 110\r\n"
                		+ "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
		content = result.getResponse().getContentAsString();        
        Result<String> resultStr = mapper.readValue(content, Result.class);
        Assert.assertSame("Result should be failure",false,resultStr.isSuccess());
        Assert.assertSame("Error List Size should be 1",1,resultStr.getErrors().size());
    }
}
