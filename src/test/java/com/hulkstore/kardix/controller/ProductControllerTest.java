package com.hulkstore.kardix.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hulkstore.kardix.model.Product;
import com.hulkstore.kardix.service.impl.ProductServiceImpl;
import com.hulkstore.kardix.service.impl.ProductServiceImplTest;
import com.hulkstore.kardix.utils.Util;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplTest.class);
	
	private MockMvc mvc;
	
    @InjectMocks
    private ProductController productController;
	
    @Mock
    private ProductServiceImpl productService;
    
    @Autowired
    private TestEntityManager entityManager;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Product> jsonProduct;
    
    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
        //productService = Mockito.mock(ProductServiceImpl.class);
    }
    
    @Test
    public void whenGetProductByIdThenReturn200() throws Exception {
    	setup();
        Mockito.when(productService.findById(1L)).thenReturn(Util.createProduct());
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
        		jsonProduct.write(Util.createProduct()).getJson()
        );
    }

    @Test
    public void whenGetProductByIdThatNotExistThenReturn200() throws Exception {
    	setup();
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/products/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void whenGetProductListByIdThenReturn200() throws Exception {
    	setup();
    	List<Product> productsList = new ArrayList<Product>();
    	productsList.add(Util.createProduct());
    	
        Mockito.when(productService.findAll()).thenReturn(productsList);
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    
    
}
