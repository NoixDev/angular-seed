package br.com.acelera.jersey.models;

public class Project {
	private long id;
	private String title;
	private String dates;
	private String description;
	private String imagesUrl;
	private String urlSite;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public String getUrlSite() {
		return urlSite;
	}
	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}
	
}
