package com.pawan.service;

import com.pawan.entity.User;

public interface UserService {
	
	public User createUser(User user);
	
	public void removeSessionMessage();
	
	public User loginUser(String email, String password);

}
