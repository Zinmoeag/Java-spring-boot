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
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "company_id_seq")
    private Long id;

    private String name;

    private String address;

    @Column(nullable = false,  unique = true, length = 150)
    private String email;

    private String phone;

    @Column(nullable = true, length = 150)
    private String website;
}
