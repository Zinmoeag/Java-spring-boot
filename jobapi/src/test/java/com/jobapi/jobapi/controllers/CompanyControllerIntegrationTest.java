package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.entities.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public CompanyControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCompanyCreateControllerWorked() throws Exception {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name("Company 1")
                .address("Address 1")
                .email("dev@gmail.com")
                .phone("123456789")
                .build();
        String companyJson = objectMapper.writeValueAsString(companyEntity);
        System.out.println(companyEntity.toString() + "sout");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public  void testThatCompaniesFindManyWorked() throws Exception {
        CompanyEntity company = CompanyEntity.builder()
                .name("Company 1")
                .address("Address 1")
                .email("dev@gmail.com")
                .phone("123456789")
                .build();

        MockMvcRequestBuilders.post("/companies")
            .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Company 1")
        );
    }
}
