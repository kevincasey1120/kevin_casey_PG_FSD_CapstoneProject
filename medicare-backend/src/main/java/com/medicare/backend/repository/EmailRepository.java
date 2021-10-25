package com.medicare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.backend.model.Role;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Repository
public interface EmailRepository extends JpaRepository<Role,Integer>{

}
