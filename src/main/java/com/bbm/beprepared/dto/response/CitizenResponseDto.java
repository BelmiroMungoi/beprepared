package com.bbm.beprepared.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenResponseDto {

    private Long id;
    private String phone;
    private String deviceId;
    private String province;
    private String city;
    private boolean verified;
}
