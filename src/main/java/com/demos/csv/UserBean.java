package com.demos.csv;

import java.util.Objects;

import com.opencsv.bean.CsvBindByName;

public class UserBean{
	
	//@CsvBindByName
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	/*public UserPOJO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}*/
	
	public UserBean()
	{
		
	}
	
	

}
