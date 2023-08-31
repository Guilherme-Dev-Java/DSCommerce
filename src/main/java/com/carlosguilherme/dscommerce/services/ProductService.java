package com.carlosguilherme.dscommerce.services;

import org.hibernate.tool.schema.internal.StandardAuxiliaryDatabaseObjectExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carlosguilherme.dscommerce.dto.ProductDTO;
import com.carlosguilherme.dscommerce.entities.Product;
import com.carlosguilherme.dscommerce.repositories.ProductRepository;
import com.carlosguilherme.dscommerce.services.execptions.DatabaseExecption;
import com.carlosguilherme.dscommerce.services.execptions.ResourceNotFoundExecption;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundExecption("Recurso não encontrado."));
		return new ProductDTO(product);
		
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
			
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new ProductDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExecption("Recurso não encontrado");
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundExecption("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExecption("Falha de integridade referencial");
		}
		
		
	}

	private void copyDtoEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
}
