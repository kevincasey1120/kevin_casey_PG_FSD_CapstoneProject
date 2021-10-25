package com.medicare.backend.services;

import java.util.List;
import com.medicare.backend.model.Medicine;


public interface AdminService {

	// Add   medicine
	public Medicine createMedicine(Medicine medicine) throws Exception;
	//Update medicine
	public Medicine updateMedicine(int medid,Medicine medicine);
	//Remove medicine
	public void removeMedicine(int medid);
	//View all medicines;
	public List<Medicine> getAllMedicines();
	//Get medicine by id
	public Medicine getMedicineByID(int medid);
	//Get medicine by name
	public Medicine getMedicineByName(String medname);
	
}
