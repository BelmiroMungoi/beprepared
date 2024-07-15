package com.bbm.beprepared.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CitizenRequestDto {

    @NotBlank
    @Size(min = 9, max = 9)
    private String phone;
    private String deviceId;
    @NotNull
    private Long cityId;
}
