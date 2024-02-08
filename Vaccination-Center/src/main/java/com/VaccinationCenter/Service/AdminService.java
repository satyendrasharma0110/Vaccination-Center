package com.VaccinationCenter.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.VaccinationCenter.Entities.Admin;
import com.VaccinationCenter.Repository.AdminRepository;


@Service
public class AdminService {

	
	@Autowired
	AdminRepository adminRepo ;
	
	public void addAdmin(Admin admin){
        adminRepo.save(admin);
    }
	
	public boolean checkLogin(@RequestParam("email") String email , @RequestParam("password") String password) {
		return adminRepo.checkLoginRepo(email, password);
    }
		
	public Admin getAdminByEmail(String email) {
        List<Admin> admins = adminRepo.findByEmail(email);        
        if (!admins.isEmpty()) {
            return admins.get(0); // Returning the first admin from the list
            
        }
        return null; // No admin found with the given email
    }

	public Admin findByEmailAndPassword(String email,String password) {
		Admin authenticatedAdmin = adminRepo.findByEmailAndPassword(email,password);
       
		return authenticatedAdmin;
	}
}
