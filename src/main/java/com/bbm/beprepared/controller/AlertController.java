package com.bbm.beprepared.controller;

import com.bbm.beprepared.dto.request.AlertRequestDto;
import com.bbm.beprepared.dto.response.AlertResponseDto;
import com.bbm.beprepared.mapper.Mapper;
import com.bbm.beprepared.service.AlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alerts")
@Tag(name = "2. Alert Controller")
public class AlertController {

    private final Mapper mapper;
    private final AlertService alertService;

    @PostMapping("/")
    public ResponseEntity<String> createAlert(@Valid @RequestBody AlertRequestDto alertRequestDto) {
        return new ResponseEntity<>(alertService.createAlert(
                mapper.mapAlertRequestToModel(alertRequestDto),
                alertRequestDto.getCityId(),
                alertRequestDto.getProvinceId()),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertResponseDto>> getAllAlerts() {
        return ResponseEntity.ok(mapper.mapAlertToResponseDtoList(
                alertService.getAllAlerts()
        ));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlertResponseDto>> getAllActiveAlerts() {
        return ResponseEntity.ok(mapper.mapAlertToResponseDtoList(
                alertService.getAllActiveAlerts()
        ));
    }

    @GetMapping("/city")
    public ResponseEntity<List<AlertResponseDto>> getAllAlertsByCity(@RequestParam Long cityId) {
        return ResponseEntity.ok(mapper.mapAlertToResponseDtoList(
                alertService.getAllAlertsByCityId(cityId)
        ));
    }

    @GetMapping("/province")
    public ResponseEntity<List<AlertResponseDto>> getAllAlertsByProvince(@RequestParam Long provinceId) {
        return ResponseEntity.ok(mapper.mapAlertToResponseDtoList(
                alertService.getAllAlertsByProvinceId(provinceId)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertResponseDto> getAlertById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapAlertToResponseDto(
                alertService.getAlertById(id)
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> activeAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.activeAlert(id));
    }
}
