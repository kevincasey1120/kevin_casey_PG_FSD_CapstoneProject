package com.medicare.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Entity
@Table(name = "cartitems")
public class CartItems {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int itemsid;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id",referencedColumnName = "medid")
    private Medicine medicine;
    
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
 
	public CartItems() {
	}

	public int getItemsid() {
		return itemsid;
	}

	public void setItemsid(int itemsid) {
		this.itemsid = itemsid;
	}
	
	
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
   
}
