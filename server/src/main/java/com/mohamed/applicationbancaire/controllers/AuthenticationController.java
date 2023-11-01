package com.mohamed.applicationbancaire.controllers;

import com.mohamed.applicationbancaire.dtos.UserDto;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationRequest;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationResponse;
import com.mohamed.applicationbancaire.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(userService.autenticate(request));
    }

}
