package com.springboot.examples.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.examples.model.IPInfo;
/**
 * 
 * @author Niran
 * JPA Repository for IPEntity
 */
public interface IPRepository extends JpaRepository<IPInfo, Long>{
	List<IPInfo> findByValue(String value);
}
