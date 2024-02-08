package com.VaccinationCenter.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.VaccinationCenter.Entities.VaccinationCenter;

public interface VaccinationCenterRepository extends CrudRepository<VaccinationCenter, Integer> {

	List<VaccinationCenter> findByCenterName(String centerName);
	
	List<VaccinationCenter> findAll();
}
