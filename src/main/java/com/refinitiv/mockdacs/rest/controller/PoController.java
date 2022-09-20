package com.refinitiv.mockdacs.rest.controller;

import com.refinitiv.mockdacs.rest.response.PoDTO;
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
@RequestMapping("/api/v1/po")
public class PoController {

    @Autowired
    PoService poService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(poService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PoDTO poDTO) {
        return ResponseEntity.ok(poService.save(poDTO));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody PoDTO poDTO) {
        return ResponseEntity.ok(poService.save(poDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") Long id) {
        poService.deleteById(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity searchFundMappingByCriteria() {
//        return ResponseEntity.ok("1");
//    }
}
