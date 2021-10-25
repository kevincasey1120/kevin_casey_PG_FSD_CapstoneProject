package com.medicare.backend.services;

import java.util.List;
import java.util.Set;

import com.medicare.backend.exception.UserFoundException;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.model.User;
import com.medicare.backend.model.UserRole;

public interface UserService {

	//Creating user
	public User createUser(User user,Set<UserRole> userroles) throws UserFoundException;
	
	//getting user
	public User getUser(int userid);
	
	//update user password
	public void updatePassword(int userid,String password);
	
	//deleting user
	public void deleteUser(int userid);
	
	//View all medicines
	public List<Medicine> getAllMedicines();
	
	//Get medicine by id
	public Medicine getMedicineByID(int medid);
	
	//update user address
	public void updateAddress(int userid,String address);
	

	//Get Medicine by category
	public List<Medicine> getAllMedicinesByCategory(String category);
	
	//Get Medicine by medname
	public List<Medicine> getAllMedicinesByName(String medname);
}
