package com.order.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.order.demo.domain.Ordersdemo;

/**
 * @author Anuj Kumar
 * 
 * This class represent repository for orders table
 */
@Repository
public interface OrdersdemoRepository extends CrudRepository<Ordersdemo, Integer>{

	@Query("from Ordersdemo o where o.id = :id")
	Ordersdemo findOrderByOrdersId(@Param("id") int id);
		
}