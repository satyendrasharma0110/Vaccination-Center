package com.VaccinationCenter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VaccinationCenter.Entities.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

	List<Citizen> findByCenterCenterName(String centerName);

}
