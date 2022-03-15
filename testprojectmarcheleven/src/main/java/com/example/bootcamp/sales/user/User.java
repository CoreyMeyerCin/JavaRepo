package com.example.bootcamp.sales.user;

import javax.persistence.*;

@Entity
//@Table(name="user", uniqueConstraints= {@UniqueConstraint(columnNames= {"name", "displayName","email"})})


public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name",length=30, nullable=false, unique=true)
	private String name;
	@Column(name="displayName",length=18, nullable=false, unique=true)
	private String displayName;
	@Column(name="email",length=255, nullable=false, unique=true)
	private String email;
	@Column(length=30, nullable=false)
	private String password;
	private String status = "NEW";
	
	
	
	////////////////////////////////
	public User() {}
	////////////////////////////////



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDisplayName() {
		return displayName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

}
