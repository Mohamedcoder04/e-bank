package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.TransactionDto;
import com.mohamed.applicationbancaire.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "transaction")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/transaction/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/transaction/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("user-id") Integer id){
        return ResponseEntity.ok(service.findAllByUserId(id));
    }

}
