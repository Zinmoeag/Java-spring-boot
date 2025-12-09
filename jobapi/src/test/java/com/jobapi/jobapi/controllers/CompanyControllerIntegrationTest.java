package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.dtos.CompanyDTO;
import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.services.CompanyService;
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
    private CompanyService companyService;

    @Autowired
    public CompanyControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc,  CompanyService companyService) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.companyService = companyService;
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
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Company 1")
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

        companyService.save(company);

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

    @Test
    public void testThatCompaniesCanBeUpdateIftisisExist() throws Exception {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name("Company 1")
                .address("Address 1")
                .email("email")
                .build();
        CompanyEntity insertResult = companyService.save(companyEntity);

        CompanyEntity newCompanyEntity = CompanyEntity.builder()
                        .name("company 2")
                                .address("addresss")
                                        .email("email")
                                                .build();
        String json = objectMapper.writeValueAsString(newCompanyEntity);
        System.out.println(json + "josn ===============================");

        mockMvc.perform(
            MockMvcRequestBuilders.post("/companies/" + insertResult.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("company 2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address").value("addresss")
        );
    }

    @Test
    public void testThatCompanyCanBeDeletedifExist() throws Exception {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name("Company 1")
                .address("Address 1")
                .email("email")
                .build();

        CompanyEntity createCompanyEntity = companyService.save(companyEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/companies/" + createCompanyEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    public void testThatCompanyCanBePartialUpdateifAlreadyExist() throws Exception {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name("Company 1")
                .address("Address 1")
                .email("email@gmail.com")
                .build();
        CompanyEntity result =  companyService.save(companyEntity);

        CompanyDTO updateCompanyDto = CompanyDTO.builder()
                .name("Company updated")
                .build();
        String json = objectMapper.writeValueAsString(updateCompanyDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/companies/" + result.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Company updated")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address").value("Address 1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("email@gmail.com")
        );
    }
}
