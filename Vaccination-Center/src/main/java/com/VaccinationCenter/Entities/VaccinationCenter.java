package com.VaccinationCenter.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String centerName;
	private String city;
	//check status 
	private String status;
		
	@OneToOne(mappedBy = "center")
	private Citizen citizen;

	@Override
	public String toString() {
	    return "VaccinationCenter [centerName=" + centerName + "]";
	}
	
}
