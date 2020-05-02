package com.hulkstore.kardix.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import lombok.Data;


@Table(name = "product_inventories")
@Entity
@Data
public class ProductInventory{
	
	static public String ACTION_IN = "inventory_entry";
	static public String ACTION_OUT = "inventory_release";
	
	ProductInventory(){};
	ProductInventory(long id, long productId, int count, int countTotal, 
			   		 String action, double price, double total){
		this.id = id;
		this.productId = productId;
		this.action = action;
		this.count = count;
		this.countTotal = countTotal;
		this.price = price;
		this.total = total;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private long id;
	
	@Column(name= "action", nullable = false)
    private String action;
	
	@Min(value= 0)
	@Column(name= "count", nullable = false)
    private int count;
	
	@Min(value= 0)
	@Column(name= "count_total", nullable = false)
    private int countTotal;
	
	@Min(value= 0)
	@Column(name= "price", nullable = false)
    private double price;
	
	@Min(value= 0)
	@Column(name= "total", nullable = false)
    private double total;

	@Column(name= "product_id", nullable = false)
	private long productId;
	
	@Temporal(TemporalType.DATE)
	@Column(name= "created_at_date", nullable = false)
	private Date createdAtDate;
	
	@Temporal(TemporalType.TIME)
	@Column(name= "created_at_time", nullable = false)
	private Date createdAtTime;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
		
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public String getAction() {
		return action;
	}
	
	public int getCountTotal() {
		return countTotal;
	}
	
	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Date getCreatedAtDate() {
		return createdAtDate;
	}
	public void setCreatedAtDate(Date createdAtDate) {
		this.createdAtDate = createdAtDate;
	}
	
	public Date getCreatedAtTime() {
		return createdAtTime;
	}
	
	public void setCreatedAtTime(Date createdAtTime) {
		this.createdAtTime = createdAtTime;
	}
	
}
