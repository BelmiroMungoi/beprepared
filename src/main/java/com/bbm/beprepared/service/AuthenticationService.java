package com.bbm.beprepared.service;

import com.bbm.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.bbm.beprepared.dto.request.AuthenticationRequestForUser;
import com.bbm.beprepared.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest);
}
