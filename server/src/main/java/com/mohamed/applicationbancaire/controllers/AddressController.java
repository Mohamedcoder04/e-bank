package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.AddressDto;
import com.mohamed.applicationbancaire.services.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Tag(name = "address")
public class AddressController {

    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/address/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete-address/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable("address-id") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

}
