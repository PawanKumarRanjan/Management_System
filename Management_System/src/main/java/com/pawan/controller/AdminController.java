package com.pawan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawan.entity.Admin;
import com.pawan.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService;
	
	@PostMapping("/create-admin")
	public String createAdmin(@ModelAttribute @Valid Admin admin, BindingResult bindingResult, HttpSession session) {
	    try
	    {
	    	if (bindingResult.hasErrors()) {
		        StringBuilder errorMsg = new StringBuilder();
		        for (FieldError error : bindingResult.getFieldErrors()) {
		            errorMsg.append(error.getDefaultMessage()).append("; ");
		        }
		        session.setAttribute("msg", errorMsg.toString());
		        return "redirect:/admin/register";
		    } else {
		        adminService.createAdmin(admin);
		        session.setAttribute("msg", "Admin Registered Successfully");
		        return "redirect:/admin/register";
		    }
	    }catch (IllegalArgumentException e) {
	        session.setAttribute("msg", "Email already exists");
	        return "redirect:/admin/register";
	    }
	}
	
	@PostMapping("/signin-admin")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
	    try {
	        Admin admin = adminService.loginAdmin(email, password);
	        session.setAttribute("admin", admin);
	        session.setAttribute("msg", ""); 
	        return "redirect:/adminWelcome";
	    } catch (IllegalArgumentException e) {
	        session.setAttribute("msg", "Invalid email or password");
	        return "redirect:/admin/login";
	    }
	}
	
	@GetMapping("/logoutAdmin")
    public String yourMethod(HttpSession session) {
    	session.removeAttribute("admin");
    	session.invalidate();
        return "redirect:/admin/login";
    }
	
}
