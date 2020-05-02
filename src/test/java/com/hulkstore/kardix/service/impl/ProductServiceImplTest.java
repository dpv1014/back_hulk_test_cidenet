package com.hulkstore.kardix.service.impl;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hulkstore.kardix.model.Product;
import com.hulkstore.kardix.service.ProductService;
import com.hulkstore.kardix.utils.Util;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceImplTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplTest.class);
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ProductService productService;
		
	@Test
	public void whenCreateProductWithoutNameThenShouldReceiveException() {
		Product product = Util.createProduct();
		product.setName(null);
		try {
		    entityManager.persist(product);
		    fail("Expected an Exception null constraint to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.persistence.PersistenceException");
		}
	}
	
	@Test
	public void whenCreateProductWithoutDescriptionThenShouldReceiveException() {
		Product product = Util.createProduct();
		product.setDescription(null);
		try {
		    entityManager.persist(product);
		    fail("Expected an Exception null constraint name to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.persistence.PersistenceException");
		}
	}
	
	@Test
	public void whenCreateProductWithtPriceNegativeThenShouldReceiveException() {
		Product product = Util.createProduct();
		product.setPrice(-1);
		try {
		    entityManager.persist(product);
		    fail("Expected an Exception negative constraint count to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.validation.ConstraintViolationException");
		}
	}
	
	@Test
	public void whenCreateProductWithtCountNegativeThenShouldReceiveException() {
		Product product = Util.createProduct();
		product.setCount(-1);
		try {
		    entityManager.persist(product);
		    fail("Expected an Exception negative constraint count to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.validation.ConstraintViolationException");
		}
	}
	
	@Test
	public void whenCreateProductThenShouldExistProduct() {
		Product product = Util.createProduct();
		entityManager.persist(product);
		
		Assert.assertEquals(1, productService.findAll().toArray().length);
	}
		
}
