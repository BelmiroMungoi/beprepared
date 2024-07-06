package com.bbm.beprepared.repository;

import com.bbm.beprepared.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByProvinceId(Long provinceId);
}
