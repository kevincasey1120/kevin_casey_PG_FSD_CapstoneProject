package com.medicare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.backend.model.User;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
    /**
     * @param username
     * @return
     */
    public User findByUsername(String username);
    
    /**
     * @param userid
     * @return
     */
    public User findById(int userid);
}
