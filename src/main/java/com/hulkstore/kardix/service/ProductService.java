package com.hulkstore.kardix.service;

import java.util.List;

import com.hulkstore.kardix.model.Product;

public interface ProductService {
	public abstract List<Product> findAll();
	public abstract void create(Product product);
	public abstract Product update(Long id, Product product);
	public abstract void delete(long id);
}
