package com.hulkstore.kardix.repository;

import com.hulkstore.kardix.model.ProductInventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
	public List<ProductInventory> findAllByProductId(long productId);
	
	
	@Query(value= "SELECT CREATED_AT_DATE, ACTION, SUM(COUNT) AS COUNT, SUM(COUNT_TOTAL) AS COUNT_TOTAL, PRICE "
			+ "FROM PRODUCT_INVENTORIES "
			+ "WHERE PRODUCT_ID = ?1 "
			+ "GROUP BY PRODUCT_ID, PRICE, ACTION, CREATED_AT_DATE "
			+ "ORDER BY CREATED_AT_DATE DESC",
			  nativeQuery = true)
	public List<Object> getKardexSummaryByProductId(long productId);
}