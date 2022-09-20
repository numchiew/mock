package com.refinitiv.mockdacs.rest.controller;

import com.refinitiv.mockdacs.rest.response.LicenseDTO;
import com.refinitiv.mockdacs.rest.response.PoDTO;
import com.refinitiv.mockdacs.rest.service.LicenseService;
import com.refinitiv.mockdacs.rest.service.PoService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/v1/license")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(licenseService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody LicenseDTO dto) {
        return ResponseEntity.ok(licenseService.save(dto));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody LicenseDTO dto) {
        return ResponseEntity.ok(licenseService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") Long id) {
        licenseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
