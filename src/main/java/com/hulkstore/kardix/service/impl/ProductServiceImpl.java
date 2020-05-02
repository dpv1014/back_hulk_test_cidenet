package com.hulkstore.kardix.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.kardix.repository.ProductRepository;
import com.hulkstore.kardix.service.ProductService;
import com.hulkstore.kardix.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}

	@Override
	public void create(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product updateProduct) {
		// TODO Auto-generated method stub
		
		Product product = this.findById(id);
		if(product == null) {
			return null;
		}
		
		product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setImageUrl(updateProduct.getImageUrl());
        product.setActive(updateProduct.getActive());
        
        return productRepository.save(product);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Product product = productRepository.getOne(id);
		productRepository.delete(product);
		
	}

	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent())
			return product.get();
		return null;
	}
		
}
