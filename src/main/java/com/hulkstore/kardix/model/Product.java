package com.hulkstore.kardix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("Model Product")
@Table(name = "products")
@Entity
@Data
public class Product{
	public Product(){};
	public Product(long id, String name, String description, String imageUrl, int count, 
			double price, boolean active){
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.count = count;
		this.price = price;
		this.active = active;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@ApiModelProperty(value = "the product's id", required = true)
    private long id;
	
	@ApiModelProperty(value = "the product's name", required = true)
	@Size(min = 1, max = 255)
	@Column(name="name", nullable = false)
    private String name;
	
	@ApiModelProperty(value = "the product's description", required = true)
	@Size(min = 1, max = 255)
	@Column(name="description", nullable = false)
    private String description;
	
	@ApiModelProperty(value = "the product's imageUrl", required = true)
	@Size(min = 1, max = 255)
	@Column(name= "image_url", nullable = false)
    private String imageUrl;
	
	@ApiModelProperty(value = "the product's count", required = true)
	@Min(value= 0)
	@Column(name= "count", nullable = false)
    private int count;
	
	@ApiModelProperty(value = "the product's price", required = true)
	@Min(value= 0)
	@Column(name= "price", nullable = false)
    private double price;
	
	@Column(name= "active", nullable = false)
    private boolean active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
