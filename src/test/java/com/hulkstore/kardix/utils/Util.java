package com.hulkstore.kardix.utils;

import com.hulkstore.kardix.model.Product;

public class Util {
	
	public static Product createProduct() {
			Product product = new Product();
			product.setName("Producto 1");
			product.setDescription("Producto 1 - Description");
			product.setPrice(1500L);
			product.setImageUrl("xxxxx");
			product.setActive(true);
			product.setCount(0);
			
			return product;
	}
	
}
