package com.carlosguilherme.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosguilherme.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}
