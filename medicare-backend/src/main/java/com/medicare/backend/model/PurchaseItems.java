package com.medicare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author fsd developer:  kevin casey
 *
 */
@Entity
@Table(name = "purchaseitems")
public class PurchaseItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchaseitemid;
	
	//--------------
	private int userid;
	
	@Column(name = "medname")
	private String medname;
	
	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "price")
    private int price;
    
	public PurchaseItems() {
		
	}

	public int getPurchaseitemid() {
		return purchaseitemid;
	}

	public void setPurchaseitemid(int purchaseitemid) {
		this.purchaseitemid = purchaseitemid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
   
}
