package com.hulkstore.kardix;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hulkstore.kardix.controller.*;

@SpringBootTest
class KardixApplicationTests {

	@Autowired
	private ProductController productController;
	
	@Autowired
	private ProductInventoryController productInventoryController;
	
	@Test
	void contextLoads() throws Exception {
	}

}
