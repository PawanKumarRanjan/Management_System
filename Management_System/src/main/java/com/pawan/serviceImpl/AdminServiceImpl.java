package com.pawan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pawan.entity.Admin;
import com.pawan.repository.AdminRepository;
import com.pawan.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public Admin createAdmin(Admin admin) {
	    if (adminRepo.existsByEmail(admin.getEmail())) {
	        throw new IllegalArgumentException("Email already exists");
	    }
	    Admin createdAdmin = adminRepo.save(admin);
	    return createdAdmin;
	}

	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}
	
	@Override
	public Admin loginAdmin(String email, String password) {
	    Admin admin = adminRepo.findByEmail(email);
	    if (admin != null && admin.getPassword().equals(password)) {
	        return admin;
	    } else {
	        throw new IllegalArgumentException("Invalid email or password");
	    }
	}

}
