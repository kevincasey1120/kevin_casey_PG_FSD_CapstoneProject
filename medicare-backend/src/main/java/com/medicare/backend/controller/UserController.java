package com.medicare.backend.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.backend.exception.UserFoundException;
import com.medicare.backend.model.CartItems;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.model.PurchaseItems;
import com.medicare.backend.model.Role;
import com.medicare.backend.model.User;
import com.medicare.backend.model.UserRole;
import com.medicare.backend.services.CartService;
import com.medicare.backend.services.PurchaseService;
import com.medicare.backend.services.UserService;
import com.medicare.backend.services.EmailService;


/**
 * @author fsd developer:  kevin casey
 *
 */

//@CrossOrigin(origins = "http://ec2-18-116-81-29.us-east-2.compute.amazonaws.com") // ACCEPTS DATA To-FROM 'FRONTEND' URL
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")   // ? @RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//=================================== USER ======================================
	 /**
		 * @param user
		 * @return
		 * @throws Exception
		 */
		@PostMapping("/")
		public User createUser(@RequestBody User user) throws Exception {
			
			Set<UserRole> userroles=new HashSet<>();
			
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			
			//-----------------------------------------
			//> Set Role
			Role role=new Role();
			role.setRoleId(222);
			role.setRoleName("ROLE_USER");
			
			//-----------------------------------------
			//> Set USER-ROLE
			UserRole userrole=new UserRole();
			userrole.setRole(role);
			userrole.setUser(user);
			userroles.add(userrole);
			
			//-----------------------------------------
			//> CREATE NEW USER
			return this.userService.createUser(user, userroles);
		}
		
		
		 /**
	     * @param ex
	     * @return
	     */
	    @ExceptionHandler(UserFoundException.class)	
	    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
	        return ResponseEntity.ok(ex.getMessage());
	    }
	
	
	/**
	 * @param userid
	 * @return
	 */
	@GetMapping("/{userid}")
	public User getUser(@PathVariable("userid") int userid) {
		return this.userService.getUser(userid);
	}
	
	
	/**
	 * @param userid
	 */
	@DeleteMapping("/{userid}")
	public void deleteUser(@PathVariable("userid") int userid) {
		this.userService.deleteUser(userid);
	}
	
	
	//=============================== MEDICINES ======================================
	/**
	 * @param medid
	 * @return
	 */
	@GetMapping("/getmedicine/{medid}")
	public Medicine getMedicineByID(@PathVariable("medid") int medid){
		return this.userService.getMedicineByID(medid);
	}
	
	
	/**
	 * @return
	 */
	@GetMapping("/getmedicines")
	public List<Medicine> getListOfMedicines(){
		List<Medicine> medicines=this.userService.getAllMedicines();
		return medicines;
	}
	
	/**
	 * @param category
	 * @return
	 */
	@GetMapping("/getmedicinesbycat/{category}")
	public List<Medicine> getMedicinesByCategory(@PathVariable("category") String category){
		List<Medicine> medicines=this.userService.getAllMedicinesByCategory(category);
		return medicines;
	}
	

	/**
	 * @param category
	 * @return
	 */
	@GetMapping("/getmedicinesbyname/{medname}")
	public List<Medicine> getMedicinesByName(@PathVariable("medname") String medname){
		List<Medicine> medicines=this.userService.getAllMedicinesByName(medname);
		return medicines;
	}
	
	
    /**
     * @param userid
     * @return
     */
    @GetMapping("/getusercart/{userid}")
	public Set<CartItems> getListOfMedicines(@PathVariable("userid") int userid){
		Set<CartItems> Cartitems=this.cartService.getAllCartitemsByUser(userid);
		return Cartitems;
	}
	
	
	//=============================== CART / PURCHASE ======================================
    /**
     * @param addCartRequest
     */
    @PostMapping("/addtocart")
    public void addToCart(@RequestBody HashMap<String,String> addCartRequest) {
    	int userid=Integer.parseInt(addCartRequest.get("userid"));
    	int medid=Integer.parseInt(addCartRequest.get("medid"));
    	int quantity=Integer.parseInt(addCartRequest.get("quantity"));
    	
        this.cartService.addToCart(userid, medid, quantity);
    }
    
    /**
     * @param itemsid
     */
    @DeleteMapping("/removeitem/{itemsid}")
	public void deleteCartitem(@PathVariable("itemsid") int itemsid) {
		this.cartService.deleteByMedicine(itemsid);
	}
    
    /**
     * @param userid
     * @return
     */
    @PostMapping("/purchaseitems/{userid}")
    public Set<PurchaseItems> purchaseItems(@PathVariable("userid") int userid){
    	return this.purchaseService.getPurchaseItems(userid);
    }
    
    
    /**
     * @param updateDetails
     */
    @PostMapping("/updatequantity")
    public void updateQuantity(@RequestBody HashMap<String,String> updateDetails) {
    	int itemsid=Integer.parseInt(updateDetails.get("itemsid"));
    	int quantity=Integer.parseInt(updateDetails.get("quantity"));
    	
        this.cartService.updateQuantity(itemsid, quantity);
    }
    

  //=============================== USER MISC ======================================
    /**
     * @param updateDetails
     */
    @PostMapping("/updateaddress")
    public void updateAddress(@RequestBody HashMap<String,String> updateDetails) {
    	int userid=Integer.parseInt(updateDetails.get("userid"));
    	String address=updateDetails.get("address");
        this.userService.updateAddress(userid,address);
    }
    
    /**
     * @param updateDetails
     */
    @PostMapping("/updatepassword")
    public void updatePassword(@RequestBody HashMap<String,String> updateDetails) {
    	int userid=Integer.parseInt(updateDetails.get("userid"));
    	String password=updateDetails.get("password");
        this.userService.updatePassword(userid,password);
    }

    
    @PostMapping("/sendemail")
    public void sendEmail(@RequestBody HashMap<String,String> addCartRequest) {
    	//--------------------------------------------------
    	String from= addCartRequest.get("from");
    	String fromName= addCartRequest.get("fromName");
    	String subject= addCartRequest.get("subject");
    	String body= addCartRequest.get("body");
    	//--------------------------------------------------
    	System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  SENDING EMAIL  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("FROM: "+from+"     FROM-NAME: "+fromName+"     SUBJECT: "+subject);
    	System.out.println("MESSAGE BODY: "+body);
    	//--------------------------------------------------
    	if(from != null && from.length() > 0 
    			&& fromName != null && fromName.length() > 0 
    			&& subject != null && subject.length() > 0 
    			&& body != null && body.length() > 0 
    			) {
    	    this.emailService.sendElasticEmail(from,fromName,subject,body);
    	   	System.out.println("[EMAIL SENT! ] ------SUCCESS------- THE EMAIL WAS SENT TO MEDICARE!");
    	}else {
    	   	System.out.println("[EMAIL SEND FAILURE ] ------FAILURE------- THE DATA WAS IN-VALID!");
    	}
    	//--------------------------------------------------
      	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");
    }
}
