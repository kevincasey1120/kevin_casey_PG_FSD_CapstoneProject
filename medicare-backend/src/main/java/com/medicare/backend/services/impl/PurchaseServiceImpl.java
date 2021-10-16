package com.medicare.backend.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.model.CartItems;
import com.medicare.backend.model.PurchaseItems;
import com.medicare.backend.model.User;
import com.medicare.backend.repository.CartitemsRepository;
import com.medicare.backend.repository.PurchaseItemsRepository;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.services.PurchaseService;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartitemsRepository cartitemsRepository;
	
	@Autowired
	private PurchaseItemsRepository purchaseitemsRepository;
	 
	@Override
	public Set<PurchaseItems> getPurchaseItems(int userid) {

		// --------------------------------
		// > Create the User Record by their ID
		User user = this.userRepository.findById(userid);

		Set<PurchaseItems> purchaseitems = new HashSet<>();
		
		//-------------------------------------------------------------
		if (user != null) {
			
			Set<CartItems> cartitems = this.cartitemsRepository.findByUser(user);

			if(cartitems.size() > 0) {
				
				// -------------------------------------------------------
				// > Iterate over all cart items
				for (CartItems cartitems2 : cartitems) {
	
					// --------------------------------
					// > Create new Purchase Item
					PurchaseItems item = new PurchaseItems();
	
					// --------------------------------
					item.setMedname(cartitems2.getMedicine().getMedname());
					item.setPrice(cartitems2.getMedicine().getPrice());
					item.setQuantity(cartitems2.getQuantity());
					item.setUserid(userid);
	
					// --------------------------------------------
					// > SAVE PURCHASE to RETURN-SET
					purchaseitems.add(this.purchaseitemsRepository.save(item));
	
					// --------------------------------------------
					// > CLEAN UP OUR CART for THIS PURCHASE
					this.cartitemsRepository.deleteById(cartitems2.getItemsid());
				}
				LOG.info("<PURCHASE SERVICE>  PURCHASE-ITEMS FINALIZED -- there were ["+cartitems+"] Cart-Items Processed");
				return purchaseitems;
			
			}else {
				LOG.info("<PURCHASE SERVICE - WARNING>  CART WAS EMPTY -- NOTHING TO PURCHASE!");
				return purchaseitems;
			}
	
		}else {
			LOG.info("<PURCHASE SERVICE - ERROR>  USER RECORD NOT FOUND");
			return purchaseitems;
		}
	}
}
