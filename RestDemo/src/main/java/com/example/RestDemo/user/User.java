package com.example.RestDemo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
//@ApiModel(description = "this class contains all the user information")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=5)
//	@ApiModelProperty(notes="the name must be minimum 5 characters")
	private String name;
	
	private Date dob;
	
	@OneToMany(mappedBy="user")
	private List<Posts> post;
	
	public User(){}
	public User(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		
	}
	public List<Posts> getPost() {
		return post;
	}
	public void setPost(List<Posts> post) {
		this.post = post;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
