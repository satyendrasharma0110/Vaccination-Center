package com.VaccinationCenter.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VaccinationCenter.Entities.VaccinationCenter;
import com.VaccinationCenter.Repository.VaccinationCenterRepository;

@Service
public class VaccinationCenterService {

	
	@Autowired
	VaccinationCenterRepository vaccinationRepo;
	
	
	public void AddCenter(VaccinationCenter center){
        vaccinationRepo.save(center);
    }
	public VaccinationCenter getCenterByName(String centerName) {
		List<VaccinationCenter> centers = vaccinationRepo.findByCenterName(centerName);       
        if (!centers.isEmpty()) {
            return centers.get(0); // Returning the first vaccination center from the list
            
        }
        return null; // no vaccination center found.
	}
	
	 public List<VaccinationCenter> getAllCenters() {
	        return vaccinationRepo.findAll(); // Retrieve all centers
	    }
	 
	 
	public VaccinationCenter getbyid(int id) {		
		
		Optional<VaccinationCenter> optionalCenter = vaccinationRepo.findById(id);
        return optionalCenter.orElse(null);
	
	}
	public void deletebyid(int id) {
		
		vaccinationRepo.deleteById(id);
	}
	public void updateCenter(VaccinationCenter center) {
        vaccinationRepo.save(center);
    }
}
