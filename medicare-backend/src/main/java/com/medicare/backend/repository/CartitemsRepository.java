package com.medicare.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.backend.model.CartItems;
import com.medicare.backend.model.Medicine;
import com.medicare.backend.model.User;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Repository
public interface CartitemsRepository extends JpaRepository<CartItems,Integer> {
	
	/**
	 * @param user
	 * @return
	 */
	public Set<CartItems> findByUser(User user);
	
	/**
	 * @param itemsid
	 * @return
	 */
	public CartItems findByItemsid(int itemsid);

	/**
	 * @param medicine
	 * @param user
	 * @return
	 */
	public CartItems findByMedicineAndUser(Medicine medicine,User user);
	
}
