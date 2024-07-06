package com.bbm.beprepared.dto.request;

import com.bbm.beprepared.model.enums.Severity;
import lombok.Data;

@Data
public class AlertRequestDto {

    private String title;
    private String message;
    private Severity severity;
    private Long provinceId;
    private Long cityId;
}
