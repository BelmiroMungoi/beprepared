package com.bbm.beprepared.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestForCitizen {

    @NotBlank
    private String phone;
    @NotBlank
    private String otp;
}
