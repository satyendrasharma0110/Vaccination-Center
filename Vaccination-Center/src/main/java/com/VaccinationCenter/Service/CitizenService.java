package com.VaccinationCenter.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VaccinationCenter.Entities.Citizen;
import com.VaccinationCenter.Entities.VaccinationCenter;
import com.VaccinationCenter.Repository.CitizenRepository;

@Service
public class CitizenService {
	   @Autowired
	    private CitizenRepository citizenRepository;

	   	   
	   public Citizen saveCitizen(Citizen citizen) {
	        return citizenRepository.save(citizen);
	    }

	    
	    public Citizen updateCitizen(Citizen citizen) {
	        return citizenRepository.save(citizen);
	    }

	    
	    public List<Citizen> getAllCitizens() {
	        return citizenRepository.findAll();
	    }

	    public void addCitizen(Citizen citizen) {
	        citizenRepository.save(citizen);
	    }
	    public Citizen getCitizenById(int id) {
	        return citizenRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid citizen Id:" + id));
	    }

	    
	    public void deleteCitizenById(int id) {
	        Citizen citizen = getCitizenById(id);
	        citizenRepository.delete(citizen);
	    }		
	    
	    public Citizen getCitizensCenterByCenterName(String centerName) {
			List<Citizen> citizen = citizenRepository.findByCenterCenterName(centerName);	       
	        if (!citizen.isEmpty()) {
	            return citizen.get(0); // Returning the first admin from the list
	            
	        }
	        return null; // No admin found with the given email
		}
}
