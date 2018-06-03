package br.com.acelera.jersey.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.acelera.jersey.dao.BioDAO;
import br.com.acelera.jersey.models.Bio;
import br.com.acelera.jersey.models.Contact;
import br.com.acelera.jersey.models.OnlineCourses;
import br.com.acelera.jersey.models.Project;
import br.com.acelera.jersey.models.Schools;
import br.com.acelera.jersey.models.Skills;
import br.com.acelera.jersey.models.Work;

@Path("cv/")
public class CvController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Bio listBio() {
		Bio bio = new Bio();
		
		try {
			BioDAO dao = new BioDAO();
			
			bio = dao.listar();
			
			System.out.println(bio.toString());
			return bio;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("contact")
	public Contact listContact() {
		
		try {
			BioDAO dao = new BioDAO();
			Contact contact = dao.listarContact();
			
			return contact;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("skills")
	public List<Skills> listSkills() {
		
		try {
			BioDAO dao = new BioDAO();
			List<Skills> skills = dao.listarSkills();
	
			return skills;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("works")
	public List<Work> listWorks() {
		
		try {
			BioDAO dao = new BioDAO();
			List<Work> works = dao.listarWorks();
	
			return works;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("schools")
	public List<Schools> listSchools() {
		
		try {
			BioDAO dao = new BioDAO();
			List<Schools> schools = dao.listarSchools();
	
			return schools;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ocourse")
	public List<OnlineCourses> listOcourse() {
		
		try {
			BioDAO dao = new BioDAO();
			List<OnlineCourses> ocourse = dao.listarOcourse();
	
			return ocourse;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects")
	public List<Project> listProjects() {
		
		try {
			BioDAO dao = new BioDAO();
			List<Project> projects = dao.listarProjects();
	
			return projects;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};	
}
