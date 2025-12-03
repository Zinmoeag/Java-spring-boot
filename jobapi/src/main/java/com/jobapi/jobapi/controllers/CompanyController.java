package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.CompanyMapper;
import com.jobapi.jobapi.domains.dtos.CompanyDTO;
import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    private CompanyMapper companyMapper;
    private CompanyService companyService;

    public CompanyController(CompanyMapper companyMapper,  CompanyService companyService) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @PostMapping(path = "/companies")
    public ResponseEntity<CompanyDTO> postCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyMapper.mapFrom(companyDTO);
        CompanyEntity result = companyService.save(companyEntity);
        CompanyDTO dto = companyMapper.mapTo(result);

        return new ResponseEntity<CompanyDTO>(dto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyEntity> result = companyService.findAll();
        List<CompanyDTO> dto = result.stream().map(companyMapper::mapTo).collect(Collectors.toList());
        return new ResponseEntity<List<CompanyDTO>>(dto, HttpStatus.OK);
    }
}
