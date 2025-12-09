package com.jobapi.jobapi.domains.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id_seq")
    private Long id;

    private String title;

    private String description;

    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
