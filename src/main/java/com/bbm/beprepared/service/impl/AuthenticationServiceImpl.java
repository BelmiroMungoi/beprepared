package com.bbm.beprepared.service.impl;

import com.bbm.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.bbm.beprepared.dto.request.AuthenticationRequestForUser;
import com.bbm.beprepared.dto.response.TokenResponse;
import com.bbm.beprepared.model.Citizen;
import com.bbm.beprepared.model.Token;
import com.bbm.beprepared.model.User;
import com.bbm.beprepared.repository.CitizenRepository;
import com.bbm.beprepared.repository.TokenRepository;
import com.bbm.beprepared.repository.UserRepository;
import com.bbm.beprepared.security.JWTService;
import com.bbm.beprepared.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final CitizenRepository citizenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getPhone(),
                        authenticationRequest.getOtp()
                )
        );
        var citizen = citizenRepository.findByPhone(authenticationRequest.getPhone()).orElseThrow();
        var token = jwtService.generateToken(citizen);
        saveCitizenToken(citizen, token);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    private void saveUserToken(User user, String token) {
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

    private void saveCitizenToken(Citizen citizen, String token) {
        var jwtToken = Token.builder()
                .citizen(citizen)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }
}
