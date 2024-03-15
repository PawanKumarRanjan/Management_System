package com.pawan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawan.entity.User;
import com.pawan.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/create-user")
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult, HttpSession session) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMsg = new StringBuilder();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMsg.append(error.getDefaultMessage()).append("; ");
                }
                session.setAttribute("msg", errorMsg.toString());
                return "redirect:/user/register";
            } else {
                userService.createUser(user);
                session.setAttribute("msg", "User Registered Successfully");
                return "redirect:/user/register";
            }
        } catch (IllegalArgumentException e) {
            session.setAttribute("msg", "Email already exists");
            return "redirect:/user/register";
        }
    }

    @PostMapping("/signin-user")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            User user = userService.loginUser(email, password);
            session.setAttribute("user", user);
            session.setAttribute("msg", "");
            return "redirect:/userWelcome";
        } catch (IllegalArgumentException e) {
        	session.setAttribute("msg", "Invalid email or password");
            return "redirect:/user/login";
        }
    }
    
    @GetMapping("/logoutUser")
    public String yourMethod(HttpSession session) {
    	session.removeAttribute("user");
    	session.invalidate();
        return "redirect:/user/login";
    }
    
}