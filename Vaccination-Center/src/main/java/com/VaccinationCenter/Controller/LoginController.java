package com.VaccinationCenter.Controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.VaccinationCenter.Entities.Admin;
import com.VaccinationCenter.Entities.VaccinationCenter;
import com.VaccinationCenter.Service.AdminService;
import com.VaccinationCenter.Service.VaccinationCenterService;
@Controller
@RequestMapping("/admin")
public class LoginController {
   
	@Autowired
	AdminService service;
	
	@Autowired
	VaccinationCenterService vaccinationService;
	
	@RequestMapping("/login")
	public ModelAndView basepage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView Logout(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mv = new ModelAndView();
	    HttpSession session = request.getSession(false); // Retrieve the existing session (if any)
	    
	    if (session != null) {
	        session.invalidate(); // Invalidate the session if it exists
	    }

	    mv.addObject("message", "Your session has been logged out.");
	    mv.setViewName("login");
	    
	    return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		Admin admin = new Admin();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		 if (name.isEmpty() || name == null ||  email == null || password == null || email.isEmpty() || password.isEmpty()) {
			mv.setViewName("register");			
			mv.addObject("message", "Invalid name or password");
			
		} else {							
			 try {
		            admin.setName(name);
		            admin.setEmail(email);
		            admin.setPassword(password);
		            service.addAdmin(admin);
		            mv.setViewName("redirect:/");
		            mv.addObject(admin);
		        } catch (DataIntegrityViolationException e) {
		            mv.setViewName("register");
		            mv.addObject("message", "Email already exists");
		        } catch (Exception e) {
		            mv.setViewName("register");
		            mv.addObject("message", "Failed to save admin");
		        }
		}
		
		return mv;
	}
	
	
	@RequestMapping("/dashboard")
	public ModelAndView DashboardLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
 	
		if (email == null || email.isEmpty() && password.isEmpty()) {
			mv.setViewName("redirect:/"); //homecontroller login
			String message = "invalid";
		    mv.addObject("message", message);
		}else {
             //check login by email and password
			 Admin authenticatedAdmin = service.findByEmailAndPassword(email, password);
		        if (authenticatedAdmin != null) {
		            Admin admin = service.getAdminByEmail(email);
    				mv.setViewName("dashboard");    				
    				HttpSession httpsession = request.getSession();
    				httpsession.setAttribute("userid", admin.getName());
					mv.addObject("admin",admin);
		        } else {
		        	 mv.setViewName("login");
		        	 String message = "Invalid email or password";
		                mv.addObject("message", message);
		}
		        }
		return mv;
		
		}
	

	@RequestMapping("viewCenter")
	public ModelAndView ViewCenter(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mv = new ModelAndView();	    
	    List<VaccinationCenter> centers = vaccinationService.getAllCenters(); // Retrieve all centers from the service	    
	    mv.addObject("centers", centers); // Pass the list of centers to the view
	    mv.setViewName("viewCenter");
	    return mv;
	   }	
	}

