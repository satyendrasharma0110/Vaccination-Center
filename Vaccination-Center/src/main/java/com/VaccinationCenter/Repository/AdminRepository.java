package com.VaccinationCenter.Repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.VaccinationCenter.Entities.Admin;
public interface AdminRepository extends CrudRepository<Admin, Integer> {


    public Admin findByName(String name);
    
	@Query("SELECT r FROM Admin r WHERE r.email = :email AND r.password = :password")
	public boolean checkLoginRepo(@Param("email")String email, String password);
	
	Admin findByEmailAndPassword(String email, String password);
	
	List<Admin> findByEmail(String email);
	
}

