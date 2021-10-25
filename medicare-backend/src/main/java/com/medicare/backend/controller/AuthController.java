package com.medicare.backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.medicare.backend.config.JwtUtils;
import com.medicare.backend.exception.UserNotFoundException;
import com.medicare.backend.model.JwtRequest;
import com.medicare.backend.model.JwtResponse;
import com.medicare.backend.model.User;
import com.medicare.backend.services.impl.UserDetailsServiceImpl;

/**
 * @author fsd developer:  kevin casey
 *
 */

//@CrossOrigin(origins = "http://ec2-18-116-81-29.us-east-2.compute.amazonaws.com") // ACCEPTS DATA To-FROM 'FRONTEND' URL
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtutils;
	
	//==================================================================
	/**
	 * @param jwtRequest
	 * @return
	 * @throws UserNotFoundException
	 */
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws UserNotFoundException{
		
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		
		}catch(Exception e) {
			//--------------------------------------------------------
			//<FAILURE>  User NOT Authenticated
			//--------------------------------------------------------
			throw new UserNotFoundException("<Authentication Failed> User not found");
		}
		
		//--------------------------------------------------------
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtutils.generateToken(userDetails);
		
		//--------------------------------------------------------
		//<SUCCESS>  -- IF RESPPONSE is :OK - User Is Authenticated
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	//==================================================================
	/**
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username,String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		
		}catch(BadCredentialsException e) {
			throw new Exception("<AUTHENTICATION FAILURE>  Invalid credentials: " + e.getMessage());
		}catch(DisabledException e) {
			throw new Exception("<AUTHENTICATION FAILURE>  User record is disabled: " + e.getMessage());
		}
		
	}
	
	//==================================================================
	/**
	 * @param principal
	 * @return
	 */
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User) this.userDetailsService.loadUserByUsername(principal.getName());
	}
}
