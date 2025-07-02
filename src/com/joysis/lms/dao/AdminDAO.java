package com.joysis.lms.dao;

import com.joysis.lms.model.Admin;

public interface AdminDAO {
	
	public Admin loginAdmin(String username, String password);
	
}
