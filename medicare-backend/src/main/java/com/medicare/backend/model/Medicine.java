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
@Table(name = "medicine")
public class Medicine {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int medid;
	
	@Column(name = "medname")
	private String medname;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "dosage")
	private String dosage;

	@Column(name = "description")
	private String description;
	
	@Column(name = "enabled")
	private boolean enabled=true;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "quantity")
	private int quantity;
	
	
	public Medicine() {
	}
	

	public int getMedid() {
		return medid;
	}

	public void setMedid(int medid) {
		this.medid = medid;
	}

	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Medicine orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
