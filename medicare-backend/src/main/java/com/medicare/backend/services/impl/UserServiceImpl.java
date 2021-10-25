package com.medicare.backend.services.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.exception.UserFoundException;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.model.User;
import com.medicare.backend.model.UserRole;
import com.medicare.backend.repository.MedicineRepository;
import com.medicare.backend.repository.RoleRepository;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User getUser(int userid) {
		
		User user = this.userRepository.findById(userid);
		if(user != null) {
			LOG.info("<USER SERVICE>  (USER) RECORD --RETRIEVED-- by getUser with ID("+userid+")");
			return this.userRepository.findById(userid);
			
		}else {
			LOG.info("<USER SERVICE - ERROR>  (USER) RECORD --NOT FOUND-- for getUser with ID("+userid+")");
			return null;
		}
	}

	@Override
	public void deleteUser(int userid) {
		try {
			this.userRepository.deleteById(userid);
			LOG.info("<USER SERVICE>  (USER) RECORD --DELETED--");
			
		}catch(Exception exx) {
			LOG.info("<USER SERVICE - ERROR>  (USER) RECORD FAILED TO DELETE: "+exx.getMessage());
		}
	}
	
	@Override
	public void updateAddress(int userid, String address) {
		User user = this.userRepository.findById(userid);
		//----------------------------------------
		if(user != null) {
			user.setAddress(address);
			this.userRepository.save(user);
			LOG.info("<USER SERVICE>  (USER 'ADDRESS') --UPDATED--");
			
		}else {
			LOG.info("<USER SERVICE - ERROR>  (USER) RECORD --NOT FOUND-- FOR 'ADDRESS' UPDATE");
		}
	}

	@Override
	public void updatePassword(int userid, String password) {
		
		User user = this.userRepository.findById(userid);
		
		//----------------------------------------
		if(user != null) {
			user.setPassword(this.bCryptPasswordEncoder.encode(password));
			this.userRepository.save(user);
			LOG.info("<USER SERVICE>  (USER 'PASSWORD') --UPDATED--");
			
		}else {
			LOG.info("<USER SERVICE - ERROR>  (USER) RECORD --NOT FOUND-- FOR 'PASSWORD' UPDATE");
		}
	}


	@Override
	public List<Medicine> getAllMedicines() {
		List<Medicine> medicines = this.medicineRepository.findAll();
		return medicines;
	}

	@Override
	public Medicine getMedicineByID(int medid) {
		Medicine med = this.medicineRepository.findByMedid(medid);
		if(med != null) {
			LOG.info("<USER SERVICE>  (MEDICINE) RECORD --RETRIEVED--");
			return med;
		}else {
			LOG.info("<USER SERVICE - ERROR>  (MEDICINE) RECORD NOT FOUND");
			return null;
		}
	}

	@Override
	public List<Medicine> getAllMedicinesByCategory(String category) {
		
		List<Medicine> medicines = this.medicineRepository.findByCategoryLike(category);
		
		//---------------------------------------
		if(medicines.size() == 0) {
			LOG.info("<USER SERVICE - WRNING>  ZERO MEDICINE ITEMS FOUND for CATEGORY ("+category+")");
			
		}else {
			LOG.info("<USER SERVICE>  (LIST of MEDICINEs) of size("+medicines.size()+") --RETRIEVED for CATEGORY ("+category+")");
		}
		
		//---------------------------------------
		return medicines;
	}
	
	
	@Override
	public List<Medicine> getAllMedicinesByName(String medname) {
		
		List<Medicine> medicines = this.medicineRepository.findByMednameLike(medname);
		
		//---------------------------------------
		if(medicines.size() == 0) {
			LOG.info("<USER SERVICE - WRNING>  ZERO MEDICINE ITEMS FOUND for MEDNAME ("+medname+")");
			
		}else {
			LOG.info("<USER SERVICE>  (LIST of MEDICINEs) of size("+medicines.size()+") --RETRIEVED for MEDNAME ("+medname+")");
		}
		
		//---------------------------------------
		return medicines;
	}
	
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {

		User thisUser = this.userRepository.findByUsername(user.getUsername());

		// ---------------------------------------------------
		if (thisUser != null) {
			LOG.info("<USER SERVICE - ERROR>  (USER) RECORD ALREADY EXISTS");
			throw new UserFoundException();

		} else {
			// -----------------------------------
			for (UserRole user_role : userRoles) {
				roleRepository.save(user_role.getRole());
				LOG.info("<USER SERVICE>  USER ROLE RECORD ("+user_role.getRole().getRoleName()+") SAVED");
			}
			// ----------------------------------
			user.getUserroles().addAll(userRoles);
			
			// ----------------------------------
			LOG.info("<USER SERVICE>  --SUCCESS--  (USER) RECORD --CREATED--");
			thisUser = this.userRepository.save(user);
			
		}

		return thisUser;
	}

}
