package com.bbm.beprepared.repository;

import com.bbm.beprepared.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    boolean existsByPhone(String phone);

    Optional<Citizen> findByOtp(String otp);

    List<Citizen> findAllByCityId(Long cityId);

    List<Citizen> findAllByCityProvinceId(Long provinceId);
}
