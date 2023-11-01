package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.AccountDto;
import com.mohamed.applicationbancaire.dtos.ContactDto;
import com.mohamed.applicationbancaire.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Tag(name = "contact")
public class ContactController {

    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<ContactDto> save(@RequestBody ContactDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/contact/{contact-id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact-id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/contact/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("user-id") Integer id){
        return ResponseEntity.ok(service.findAllByUseId(id));
    }
}
