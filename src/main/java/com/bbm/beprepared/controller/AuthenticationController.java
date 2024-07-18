package com.bbm.beprepared.controller;

import com.bbm.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.bbm.beprepared.dto.request.AuthenticationRequestForUser;
import com.bbm.beprepared.dto.response.TokenResponse;
import com.bbm.beprepared.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticationRequestForUser authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthenticationRequestForCitizen authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticateCitizen(authenticationRequest));
    }
}
