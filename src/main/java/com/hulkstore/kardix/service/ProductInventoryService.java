package com.hulkstore.kardix.service;

import java.util.List;

import com.hulkstore.kardix.model.Product;
import com.hulkstore.kardix.model.ProductInventory;

public interface ProductInventoryService {
	public abstract List<ProductInventory> findAll();
	public abstract List<ProductInventory> findAllByProductId(long productId);
	public abstract void create(Product product, ProductInventory productInventory);
	public abstract List<Object> getKardexSummaryByProductId(long productId);
	
}
