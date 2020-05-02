package com.hulkstore.kardix.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.kardix.repository.ProductInventoryRepository;
import com.hulkstore.kardix.repository.ProductRepository;
import com.hulkstore.kardix.service.ProductInventoryService;
import com.hulkstore.kardix.model.*;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{
		
	@Autowired
	private ProductInventoryRepository productInventoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductInventory> findAll(){
		return productInventoryRepository.findAll();
	}
	
	@Transactional
	public void create(Product product, ProductInventory productInventory) {
		Date date = new Date();
		productInventory.setCreatedAtDate(date);
		productInventory.setCreatedAtTime(date);
		productInventory.setProductId(product.getId());
		
		int count = 0;
		if(productInventory.getAction().equals(ProductInventory.ACTION_IN)) {
			count = product.getCount() + productInventory.getCount();
		} else {
			count = product.getCount() - productInventory.getCount();
		}
		
		product.setCount(count);
		productInventory.setCountTotal(count);
		
		productInventoryRepository.save(productInventory);
		productRepository.save(product);
		
	}

	public ProductInventory findById(Long id) {
		Optional<ProductInventory> productInventory = productInventoryRepository.findById(id);
		if(productInventory.isPresent())
			return productInventory.get();
		return null;
	}

	public List<ProductInventory> findAllByProductId(long productId) {
		return productInventoryRepository.findAllByProductId(productId);
	}
	
	public List<Object> getKardexSummaryByProductId(long productId) {
		return productInventoryRepository.getKardexSummaryByProductId(productId);
	}
		
}
