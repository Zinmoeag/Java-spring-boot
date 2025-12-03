package com.jobapi.jobapi.domains.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {
    private Long id;

    private String title;

    private String description;

    private String email;

    private CompanyDTO company;
}
