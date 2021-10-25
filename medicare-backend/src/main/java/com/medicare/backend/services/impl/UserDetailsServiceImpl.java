package com.medicare.backend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.model.User;
import com.medicare.backend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User thisUser = this.userRepository.findByUsername(username);
		
		//-------------------------------------------
		if(thisUser != null) {
			LOG.info("<USER DETAILS SERVICE> --SUCCESS--  USER ("+username+") RECORD RETRIEVED");
			
		}else {
			LOG.info("<USER DETAILS SERVICE - ERROR>  USER ("+username+") RECORD WAS NOT FOUND");
			throw new UsernameNotFoundException("<USER DETAILS SERVICE - ERROR>  USER RECORD WAS NOT FOUND");
		}
		
		return thisUser;
	}

}
