package com.hulkstore.kardix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hulkstore.kardix.model.*;
import com.hulkstore.kardix.service.impl.*;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Api(value = "Products inventory api methods", description = "This API has a services for sell products and receive new products into inventory")
public class ProductInventoryController {
	
	@Autowired
	private ProductInventoryServiceImpl productInventoryService;
	
	@Autowired
	private ProductServiceImpl productService;
	

	@GetMapping("/kardex")
	public ResponseEntity<Object> kardexList() {
		return new ResponseEntity<>(productInventoryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}/kardex")
	public ResponseEntity<Object> inventoryList(@PathVariable Long productId) {
		
		if(this.productFind(productId) == null) {
			return new ResponseEntity<>("Product with id " + productId + " not found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(productInventoryService.getKardexSummaryByProductId(productId), HttpStatus.OK);
	}
	
	@PostMapping("/products/{productId}/purchase_product")
	public ResponseEntity<Object> createInventoryList(@PathVariable Long productId, @RequestBody ProductInventory productInventory) {
		
		Product product = this.productFind(productId);
		if(product == null) {
			return new ResponseEntity<>("Product with id " + productId + " not found", HttpStatus.NOT_FOUND);
		}
		
		productInventory.setAction(ProductInventory.ACTION_IN);
		productInventoryService.create(product, productInventory);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/products/{productId}/sell_product")
	public ResponseEntity<Object> sellProduct(@PathVariable Long productId, @RequestBody ProductInventory productInventory) {
		
		Product product = this.productFind(productId);
		if(product == null) {
			return new ResponseEntity<>("Product with id " + productId + " not found", HttpStatus.NOT_FOUND);
		}
		
		productInventory.setAction(ProductInventory.ACTION_OUT);
		productInventoryService.create(product, productInventory);
	    return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	private Product productFind(long id) {
        return productService.findById(id);
	}
	
}
