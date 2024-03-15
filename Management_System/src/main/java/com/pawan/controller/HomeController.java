package com.pawan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pawan.entity.Admin;
import com.pawan.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/user/register")
	public String userRegister()
	{
		return "userRegister";
	}
	
	@GetMapping("/user/login")
	public String userLogin()
	{
		return "userLogin";
	}
	
	@GetMapping("/userWelcome")
	public String userAfterLogin(Model model, HttpSession session, HttpServletRequest request) {
		 if (!session.getAttributeNames().hasMoreElements()) {
    		 return "error401";
         }
    	 else
    	 {
    		 User user = (User) session.getAttribute("user");
    		 model.addAttribute("user", user);
    		 return "userAfterLogin";
    	 }
	}
	
	@GetMapping("/admin/register")
	public String adminRegister()
	{
		return "adminRegister";
	}
	
	@GetMapping("/admin/login")
	public String adminLogin()
	{
		return "adminLogin";
	}
	
	@GetMapping("/adminWelcome")
	public String adminAfterLogin(Model model, HttpSession session, HttpServletRequest request){
		if (!session.getAttributeNames().hasMoreElements()) {
    		 return "error401";
        }
		else
		{
			Admin admin = (Admin) session.getAttribute("admin");
		    model.addAttribute("admin", admin);
		    return "adminAfterLogin";
		}
	}
	
}
