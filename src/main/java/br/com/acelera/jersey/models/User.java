package br.com.acelera.jersey.models;

public class User {
	
	private Long id;
	private String username;
	private String password;
	private String token;
	
	public User(Long id, String username, String password, String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	public User() {
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
