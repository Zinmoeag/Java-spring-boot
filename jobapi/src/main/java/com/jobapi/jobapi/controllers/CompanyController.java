package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.CompanyMapper;
import com.jobapi.jobapi.domains.dtos.CompanyDTO;
import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.services.CompanyService;
import org.hibernate.mapping.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/compaines/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id) {
        CompanyEntity result = companyService.findById(id);
        CompanyDTO dto = companyMapper.mapTo(result);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(path = "/companies/{id}")
    public ResponseEntity<CompanyDTO> postCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) throws Exception {
        if (!companyService.isExist(id)) new ResponseEntity<>(HttpStatus.NOT_FOUND);
        companyDTO.setId(id);
        CompanyEntity companyEntity = companyMapper.mapFrom(companyDTO);
        CompanyEntity result = companyService.save(companyEntity);
        return new ResponseEntity<CompanyDTO>(companyMapper.mapTo(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/companies/{id}")
    public ResponseEntity<Any> deleteCompany(@PathVariable Long id) throws Exception {
        companyService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/companies/{id}")
    public ResponseEntity<CompanyDTO> partialCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) throws Exception {
        if (!companyService.isExist(id)) new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CompanyEntity companyEntity = companyMapper.mapFrom(companyDTO);
        CompanyEntity updatedCompanyEntity = companyService.partialUpdate(id, companyEntity);
        return new ResponseEntity<CompanyDTO>(companyMapper.mapTo(updatedCompanyEntity), HttpStatus.OK);
    }
}
