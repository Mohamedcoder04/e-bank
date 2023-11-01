package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.UserDto;
import com.mohamed.applicationbancaire.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {

    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(service.findById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete-user/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") Integer userId){
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/validate-user/{user-id}") // pour dire à spring que je veux modifier un seul champs
    public ResponseEntity<UserDto> validateAccount(@PathVariable("user-id") Integer id) {
        return ResponseEntity.ok(service.validateAccount(id));
    }

    @PatchMapping("/invalidate-user/{user-id}")
    public ResponseEntity<UserDto> inValidateAccount(@PathVariable("user-id") Integer id) {
        return ResponseEntity.ok(service.inValidateAccount(id));
    }

}
