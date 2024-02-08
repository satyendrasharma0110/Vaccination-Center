package com.VaccinationCenter.Controller;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.VaccinationCenter.Entities.Citizen;
import com.VaccinationCenter.Entities.VaccinationCenter;
import com.VaccinationCenter.Service.CitizenService;
import com.VaccinationCenter.Service.VaccinationCenterService;

@Controller
@RequestMapping("/admin/vaccination")
public class VaccinationCenterController {

	@Autowired
	VaccinationCenterService vaccinationService;
	@Autowired
	CitizenService citizenService;
	
	@RequestMapping("/addCenter")
	public ModelAndView AddNewCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("addCenter");
		return mv;
	}

	@RequestMapping("/viewCenterList")
	public ModelAndView viewCenter() {
	    ModelAndView mv = new ModelAndView();

	    List<VaccinationCenter> vaccinationCenters = vaccinationService.getAllCenters();

	    // Separate vaccination centers into different categories
	    List<VaccinationCenter> activeCenters = new ArrayList<>();
	    List<VaccinationCenter> inactiveCenters = new ArrayList<>();

	    for (VaccinationCenter center : vaccinationCenters) {
	        String status = center.getStatus();
	        if (status == null) {
            inactiveCenters.add(center);
	        } 
	    }
	    mv.setViewName("viewCenter");
	    mv.addObject("centers", inactiveCenters);
	    int centerCount =  inactiveCenters.size();
        mv.addObject("centerCount", centerCount > 0 ? centerCount : 0);
	    return mv;
	}

	
	 @RequestMapping("/viewCenterById")
	   public ModelAndView viewCenterById(@RequestParam("id") int id, @RequestParam("centerName") String centerName, RedirectAttributes redirectAttributes) {
	       ModelAndView mv = new ModelAndView();
	       VaccinationCenter center = vaccinationService.getbyid(id);
	       
	       if (center != null) {
	           List<Citizen> citizens = citizenService.getAllCitizens();
	           mv.addObject("center", center);
	           mv.addObject("citizens", citizens);
	           mv.setViewName("viewCenterById");
	       } else {
	    	   redirectAttributes.addFlashAttribute("message","ID IS NOT AVAIABLE");
	           mv.setViewName("redirect:/admin/vaccination/viewCenter");
	       }
	       
	       return mv;
	   }
	 
	@RequestMapping("/insertCenter")
	public ModelAndView insertCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		VaccinationCenter center = new VaccinationCenter();
		String centerName = request.getParameter("centerName");
		String city = request.getParameter("city");
		
		
		
		
		if (centerName == null || centerName.isEmpty() && city.isEmpty()) {
			mv.setViewName("addCenter");
			mv.addObject("message", "Failed to save Center");
			
		} else {
			

			
			 try {
		            center.setCenterName(centerName);
		            center.setCity(city);
		            vaccinationService.AddCenter(center);
		            mv.setViewName("redirect:/admin/vaccination/viewCenterList");
		            VaccinationCenter vaccinationCenter = vaccinationService.getCenterByName(centerName);		            
		            mv.addObject(vaccinationCenter);
		        } catch (Exception e) {
		            mv.setViewName("addCenter");
		            mv.addObject("message", "Failed to save Center");
		        }
		}
		
		return mv;
	}
	
	
	   @RequestMapping("/editCenter")
	    public ModelAndView editCenter(@RequestParam("id") int id ,HttpServletRequest request, HttpServletResponse response) {
		   ModelAndView mv = new ModelAndView();
	        VaccinationCenter center = vaccinationService.getbyid(id);	       
	        mv.addObject("center", center);
	        mv.setViewName("editCenter");
	        return mv;
	    }

	    @RequestMapping("/deleteCenter")
	    public ModelAndView deleteCenter(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
	        vaccinationService.deletebyid(id);
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("redirect:/admin/vaccination/viewCenterList");
	        String message = " IS DELETED";
	        redirectAttributes.addFlashAttribute(id + message);
	        return mv;
	    }
	    
	    @RequestMapping("/updateCenter")
	    public ModelAndView updateCenter(@RequestParam("id") int id ,@RequestParam("centerName") String centerName ,@RequestParam("city")String city  ,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		   ModelAndView mv = new ModelAndView();
		   VaccinationCenter center = vaccinationService.getbyid(id);		  
           if(center!=null ) {
        	   center.setCenterName(centerName);
        	   center.setCity(city);
        	   vaccinationService.updateCenter(center);
        	   mv.setViewName("redirect:/admin/vaccination/viewCenterList");
        	   String message = centerName+" IS UPDATED";
        	   redirectAttributes.addFlashAttribute("message"+ message);
        	   }else {        	   
        	   mv.setViewName("redirect:/admin/vaccination/editCenter?id=" + id); // Redirect to the editCenter request mapping with the id parameter
	        mv.addObject("message", "Failed to Update Center");
           }
	        return mv;
	    }
}
