package com.bbm.beprepared.service;

import com.bbm.beprepared.model.Citizen;

import java.util.List;

public interface CitizenService {

    String createCitizen(Citizen citizen, Long cityId);

    List<Citizen> getAllCitizens();

    List<Citizen> getAllCitizensCityId(Long cityId);

    List<Citizen> getAllCitizensByProvinceId(Long provinceId);

    Citizen getCitizenById(Long id);

    String verifyAccount(String otp);
}
