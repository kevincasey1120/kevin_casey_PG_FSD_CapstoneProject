package com.medicare.backend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.repository.MedicineRepository;
import com.medicare.backend.services.AdminService;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Service
public class AdminServiceImpl implements AdminService{
	
	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private MedicineRepository medicineRepository;
	

	@Override
	public List<Medicine> getAllMedicines() {
		List<Medicine> medicines=this.medicineRepository.findAll();
		return medicines;
	}
	
	@Override
	public Medicine getMedicineByID(int Medicineid) {
		return this.medicineRepository.findByMedid(Medicineid);
	}

	@Override
	public Medicine getMedicineByName(String medname) {
		return this.medicineRepository.findByMedname(medname);
	}
	
	@Override
	public Medicine createMedicine(Medicine medicine) throws Exception {
		Medicine med=this.medicineRepository.findByMedname(medicine.getMedname());
		if(med!=null) {
			System.out.println("<ADMIN SERVICE - ERROR>  That Medicine Is Already On File");
			throw new Exception("<ADMIN SERVICE - ERROR>  That Medicine Is Already On File");
		}else {
			med=this.medicineRepository.save(medicine);
			LOG.info("<ADMIN SERVICE>  --SUCCESS--  (MEDICINE) DATABASE RECORD --CREATED--");
		}
		return med;
	}

	
	@Override
	public void removeMedicine(int medid) {
		this.medicineRepository.deleteById(medid);
		LOG.info("<ADMIN SERVICE>  --SUCCESS--  (MEDICINE) DATABASE RECORD --DELETED--");
	}
	
	
	@Override
	public Medicine updateMedicine(int medid,Medicine medicine) {
		
		//--------------------------------
		//> FIND THE MEDICINE
		Medicine med=this.medicineRepository.findByMedid(medid);
		
		//----------------------------------------
		if(med != null) {
			
			//--------------------------------
			//> POPULATE OBJECT FIELDS
			med.setMedname(medicine.getMedname());
			med.setCategory(medicine.getCategory());
			med.setPrice(medicine.getPrice());
			med.setEnabled(medicine.isEnabled());
			med.setDescription(medicine.getDescription());
			
		    //------------------------------------------------------
		    //> UPDATE DATABASE 
			LOG.info("<ADMIN SERVICE>  --SUCCESS--  (MEDICINE) DATABASE RECORD --UPDATED--");
			return this.medicineRepository.save(med);
		
		}else {
			LOG.info("<ADMIN SERVICE - ERROR>  (MEDICINE) RECORD NOT FOUND IN DATABASE");
			return null;
		}
	}

}
