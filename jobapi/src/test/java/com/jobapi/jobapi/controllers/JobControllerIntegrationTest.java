package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.services.IJobService;
import com.jobapi.jobapi.services.JobService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class JobControllerIntegrationTest {

    private MockMvc mockMvc;
    private IJobService  jobService;
    private ObjectMapper objectMapper;

    @Autowired
    public JobControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, IJobService jobService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.jobService = jobService;
    }

    @Test
    public void testThatJobControllerCanBeCreated() throws Exception {


        JobEntity jobEntity = JobEntity
                .builder()
                .title("Job Title")
                .description("Job Description")
                .email("Job Email")
                .company(null)
                .build();

        String json = objectMapper.writeValueAsString(jobEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Job Title")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value("Job Description")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("Job Email")
        );
    }

    @Test
    public void testThatJobFindAllControllerCanBeUpdated() throws Exception {
        JobEntity newJob = JobEntity.builder()
                .title("hello melo")
                .description("descrtipn")
                .email("helo mello")
                .company(null)
                .build();

        jobService.create(newJob);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("hello melo")
        );
    }
}
