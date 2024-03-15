package com.pawan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pawan.entity.User;
import com.pawan.repository.UserRepository;
import com.pawan.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User user) {
	    if (userRepo.existsByEmail(user.getEmail())) {
	        throw new IllegalArgumentException("Email already exists");
	    }
	    User newUser = userRepo.save(user);
	    return newUser;
	}

	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}
	
	@Override
	public User loginUser(String email, String password) {
	    User user = userRepo.findByEmail(email);
	    if (user != null && user.getPassword().equals(password)) {
	        return user;
	    } else {
	        throw new IllegalArgumentException("Invalid email or password");
	    }
	}
	
}
