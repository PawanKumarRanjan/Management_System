package com.pawan.service;

import com.pawan.entity.Admin;

public interface AdminService {
	
	public Admin createAdmin(Admin admin);
	
	public void removeSessionMessage();
	
	public Admin loginAdmin(String email, String password);
}
