package com.medicare.backend.services;

import java.util.Set;

import com.medicare.backend.model.PurchaseItems;

public interface PurchaseService {

	public Set<PurchaseItems> getPurchaseItems(int userid);
}
