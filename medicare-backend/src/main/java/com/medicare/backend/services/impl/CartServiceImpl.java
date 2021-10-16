package com.medicare.backend.services.impl;



import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.model.CartItems;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.model.User;
import com.medicare.backend.repository.CartitemsRepository;
import com.medicare.backend.repository.MedicineRepository;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.services.CartService;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Service
public class CartServiceImpl implements CartService{
	
	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartitemsRepository cartitemsRepository;
	
	
	public Set<CartItems> getAllCartitemsByUser(int userid){
		User user=this.userRepository.findById(userid);
		Set<CartItems> cartitems=this.cartitemsRepository.findByUser(user);
		return cartitems;
	}
	
	@Override
	public void deleteByMedicine(int itemsid) {
		this.cartitemsRepository.deleteById(itemsid);
	}

	@Override
	public void updateQuantity(int itemsid, int quantity) {
		CartItems cartitem=this.cartitemsRepository.findByItemsid(itemsid);
		cartitem.setQuantity(quantity);
		this.cartitemsRepository.save(cartitem);
	}

	@Override
	public void addToCart(int userid, int medid, int quantity) {
		
		User user = this.userRepository.findById(userid);
		Medicine medicine=this.medicineRepository.findByMedid(medid);
	    CartItems cartitem=this.cartitemsRepository.findByMedicineAndUser(medicine, user);
	    
	    //-------------------------------------------------
	    //> CHECK - For EXISTING CART-ITEM
	    if(cartitem!=null) {
	     	//---------------------------------
	    	cartitem.setQuantity(quantity);
	    	//---------------------------------
	    	LOG.info("<CART SERVICE>  (EXISTING) CART-ITEM - QUANTITY - UPDATED");
	       	
	    }else {
	    	
	    	//------------------------------------
	    	if(user != null && medicine != null) {
	    	  	cartitem=new CartItems();
		    	cartitem.setMedicine(medicine);
		    	cartitem.setUser(user);
		    	cartitem.setQuantity(quantity);
		    	//---------------------------------
		    	LOG.info("<CART SERVICE>  (NEW) CART-ITEM RECORD CREATED");
		    	
	    	}else {
	    		//---------------------------------------
	    		//> !!!! RECORD NOT FOUND !!!!
	    		if(user == null) {
	    			LOG.info("<CART SERVICE - ERROR>  USER RECORD NOT FOUND for CART-ITEM");
	    		}else {
	    			LOG.info("<CART SERVICE - ERROR>  MEDICINE RECORD NOT FOUND for CART-ITEM");
	    		}
	    	}
	  
	    }
	    
	    //------------------------------------------------------
	    //> UPDATE DATABASE - IF VALID
	    if(cartitem != null) {
	    	 this.cartitemsRepository.save(cartitem);
	 		LOG.info("<CART SERVICE>  --SUCCESS--  (MEDICINE) DATABASE RECORD UPDATED");
	    }
	}
}
