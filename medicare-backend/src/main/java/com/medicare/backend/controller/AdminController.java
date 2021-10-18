package com.medicare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.backend.model.Medicine;
import com.medicare.backend.services.AdminService;

/**
 * @author fsd developer:  kevin casey
 *
 */
@RestController
@CrossOrigin(origins = "http://ec2-18-116-81-29.us-east-2.compute.amazonaws.com") // ACCEPTS DATA To-FROM 'FRONTEND' URL
@RequestMapping("/admin")
public class AdminController {
     
	@Autowired
	private AdminService adminService;
	
	/**
	 * @param medicine
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add-medicine")
	public Medicine createMedicine(@RequestBody Medicine medicine) throws Exception {
		return this.adminService.createMedicine(medicine);
	}
	
	/**
	 * @return
	 */
	@GetMapping("/getmedicines")
	public List<Medicine> retrieveAllMedicines(){
		List<Medicine> medicines=this.adminService.getAllMedicines();
		return medicines;
	}
	
	/**
	 * @param medid
	 * @return
	 */
	@GetMapping("/getmedicine/{medid}")
	public Medicine getMedicineByID(@PathVariable("medid") int medid){
		return this.adminService.getMedicineByID(medid);
	}
	
	/**
	 * @param medid
	 * @param medicine
	 * @return
	 */
	@PostMapping("/update-medicine/{id}")
	public Medicine updateMedicineById(@PathVariable("id") int medid,@RequestBody Medicine medicine){
		return this.adminService.updateMedicine(medid, medicine);
	}
	
	/**
	 * @param medid
	 */
	@DeleteMapping("/remove-medicine/{id}")
	public void deleteMedicine(@PathVariable("id") int medid) {
		this.adminService.deleteMedicine(medid);
	}
}
