package com.project.forum.model;

/**
 * @author Vasil
 * User model containing the following attributes: Username, Password And Role
 * String Username - identifier of the user
 * String Password - password of the user
 * String Role - controls the level of access and privileges that the user has.
 */
public class User {
	private String username;
	private String password;
	private String role;
	public User(String username,String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
