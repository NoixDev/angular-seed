package br.com.acelera.jersey.models;

public class OnlineCourses {
	private long id;
	private String name;
	private String major;
	private String graduationYear;
	private String url;
	
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
