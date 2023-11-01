package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.AccountDto;
import com.mohamed.applicationbancaire.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "account")
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/account/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/account/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

}
