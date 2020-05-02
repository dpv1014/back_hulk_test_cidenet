package com.hulkstore.kardix.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import com.hulkstore.kardix.exception.ProductNotFoundException;
import com.hulkstore.kardix.model.Product;
import com.hulkstore.kardix.service.impl.ProductServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Api(value = "Products api methods", description = "This API has a CRUD for products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping("/products")
	@ApiOperation(value = "Get list of products", notes = "Return all products" )
	public ResponseEntity<Object> productsLists() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) throws ProductNotFoundException {
		productService.create(product);
	    return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get product by id", notes = "Return one product by id if exis" )
	@RequestMapping(value="/products/{id}",method = RequestMethod.GET)
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable Long id, @RequestBody Product updateProduct) {		        
        Product product = productService.update(id, updateProduct);
        if(product == null) {
        	return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
        }        
        
        return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
