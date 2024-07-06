package com.bbm.beprepared.mapper;

import com.bbm.beprepared.dto.response.CityResponseDto;
import com.bbm.beprepared.dto.response.ProvinceResponseDto;
import com.bbm.beprepared.model.City;
import com.bbm.beprepared.model.Province;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public ProvinceResponseDto mapProvinceToResponseDto(Province province) {
        return modelMapper.map(province, ProvinceResponseDto.class);
    }

    public List<ProvinceResponseDto> mapProvinceToResponseDtoList(List<Province> provinces) {
        return provinces.stream().map(this::mapProvinceToResponseDto)
                .collect(Collectors.toList());
    }

    public CityResponseDto mapCityToResponseDto(City city) {
        return modelMapper.map(city, CityResponseDto.class);
    }

    public List<CityResponseDto> mapCityToResponseDtoList(List<City> cities) {
        return cities.stream().map(this::mapCityToResponseDto)
                .collect(Collectors.toList());
    }
}
