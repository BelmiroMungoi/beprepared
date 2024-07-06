package com.bbm.beprepared.service.impl;

import com.bbm.beprepared.exception.EntityNotFoundException;
import com.bbm.beprepared.model.City;
import com.bbm.beprepared.model.Province;
import com.bbm.beprepared.repository.CityRepository;
import com.bbm.beprepared.repository.ProvinceRepository;
import com.bbm.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> getAllCitiesProvinceId(Long provinceId) {
        return cityRepository.findAllByProvinceId(provinceId);
    }

    @Override
    public Province getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("A província não foi encontrada!"));
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() ->
                new EntityNotFoundException("O distrito não foi encontrado!"));
    }
}
