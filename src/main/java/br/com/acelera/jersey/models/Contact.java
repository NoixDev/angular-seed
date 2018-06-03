package br.com.acelera.jersey.models;

public class Contact {

	private Long id;
	private String email;
	private String cell;
	private String githubUser;
	private String location;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGithubUser() {
		return githubUser;
	}
	public void setGithubUser(String githubUser) {
		this.githubUser = githubUser;
	}
}
