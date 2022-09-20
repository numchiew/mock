package com.refinitiv.mockdacs.rest.controller;

import com.refinitiv.mockdacs.rest.response.AccountDTO;
import com.refinitiv.mockdacs.rest.response.LicenseDTO;
import com.refinitiv.mockdacs.rest.service.AccountService;
import com.refinitiv.mockdacs.rest.service.LicenseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity findByName(@PathVariable(name = "name")String name) {
        return ResponseEntity.ok(accountService.findAccountByName(name));
    }

    @GetMapping("/mock")
    public ResponseEntity findByName(@RequestParam(name = "command", required = true)String command,
        @RequestParam(name = "position", required = false) String position,
        @RequestParam(name = "application", required = false) String application,
        @RequestParam(name = "service", required = false) String service,
        @RequestParam(name = "user", required = false) String user
        ) {

        if (command.equals("contentList")) {
            return ResponseEntity.ok().body(accountService.getPoForUser(service, user));
        }

        String namespace = service.replaceAll("_CONF", "");

        return ResponseEntity.ok(accountService.getAllPOForNameSpace(namespace));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AccountDTO dto) {
        return ResponseEntity.ok(accountService.save(dto));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody AccountDTO dto) {
        return ResponseEntity.ok(accountService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") Long id) {
        accountService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
