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

/**
 * @author fsd developer:  kevin casey
 *
 */
@RestController
@CrossOrigin(origins = "http://ec2-18-116-81-29.us-east-2.compute.amazonaws.com") // ACCEPTS DATA To-FROM 'FRONTEND' URL
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
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
	 * @param userId
	 */
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		this.userService.deleteUser(userId);
	}
	
	
	//=============================== MEDICINES ======================================
	/**
	 * @param category
	 * @return
	 */
	@GetMapping("/getmedicines/{category}")
	public List<Medicine> getMedicinesByCategory(@PathVariable("category") String category){
		List<Medicine> medicines=this.userService.getAllMedicinesByCategory(category);
		return medicines;
	}
	
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
    	int userid=Integer.parseInt(addCartRequest.get("userId"));
    	int medid=Integer.parseInt(addCartRequest.get("medId"));
    	int quantity=Integer.parseInt(addCartRequest.get("quantity"));
    	
        this.cartService.addToCart(userid, medid, quantity);
    }
    
    /**
     * @param itemsId
     */
    @DeleteMapping("/removeitem/{itemsId}")
	public void deleteCartitem(@PathVariable("itemsId") int itemsId) {
		this.cartService.deleteByMedicine(itemsId);
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
    	int itemsId=Integer.parseInt(updateDetails.get("itemsId"));
    	int quantity=Integer.parseInt(updateDetails.get("quantity"));
    	
        this.cartService.updateQuantity(itemsId, quantity);
    }
    

  //=============================== USER MISC ======================================
    /**
     * @param updateDetails
     */
    @PostMapping("/updateaddress")
    public void updateAddress(@RequestBody HashMap<String,String> updateDetails) {
    	int userId=Integer.parseInt(updateDetails.get("userId"));
    	String address=updateDetails.get("address");
        this.userService.updateAddress(userId,address);
    }
    
    /**
     * @param updateDetails
     */
    @PostMapping("/updatepassword")
    public void updatePassword(@RequestBody HashMap<String,String> updateDetails) {
    	int userId=Integer.parseInt(updateDetails.get("userId"));
    	String password=updateDetails.get("password");
        this.userService.updatePassword(userId,password);
    }
}
