package com.example.bootcamp.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames= {"partNbr"}))
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30,nullable=false)
	private String partNbr;
	@Column(length=30,nullable=false)
	private String name;
	@Column(columnDefinition="decimal(9,2)NOT NULL DEFAULT 0.0")
	private double price;
	
	

	//////////////////////////////////////////////
	public Product() {}
	
	
	
	//////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartNbr() {
		return partNbr;
	}
	public void setPartNbr(String partNbr) {
		this.partNbr = partNbr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
