package com.medicare.backend.services;

import java.util.Set;

import com.medicare.backend.model.CartItems;

public interface CartService {
	
   public void addToCart(int userid,int medid,int quantity);
   
   public void updateQuantity(int itemsid,int quantity);
   
   public void deleteByMedicine(int itemsid);
   
   public Set<CartItems> getAllCartitemsByUser(int userid);
}
