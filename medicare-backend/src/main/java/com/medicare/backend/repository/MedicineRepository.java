package com.medicare.backend.repository;

import org.springframework.stereotype.Repository;

import com.medicare.backend.model.Medicine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
	/**
	 * @param medid
	 * @return
	 */
	public Medicine findByMedid(int medid);

	/**
	 * @param medname
	 * @return
	 */
	public Medicine findByMedname(String medname);

	/**
	 * @param category
	 * @return
	 */
	public List<Medicine> findByCategoryLike(String category);
	
	public List<Medicine> findByMednameLike(String medname);	

}
