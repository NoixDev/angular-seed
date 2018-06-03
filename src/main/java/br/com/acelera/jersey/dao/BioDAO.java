package br.com.acelera.jersey.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.acelera.jersey.controllers.CvController;
import br.com.acelera.jersey.infra.ConexaoJDBC;
import br.com.acelera.jersey.infra.ConexaoMySqlJDBC;
import br.com.acelera.jersey.models.Bio;
import br.com.acelera.jersey.models.Contact;
import br.com.acelera.jersey.models.OnlineCourses;
import br.com.acelera.jersey.models.Project;
import br.com.acelera.jersey.models.Schools;
import br.com.acelera.jersey.models.Skills;
import br.com.acelera.jersey.models.Work;

public class BioDAO {

	private final ConexaoJDBC conexao;

	public BioDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMySqlJDBC();
	}

	public Bio listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM bio ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			Bio bio = new Bio();

			while (rs.next()) {
				bio = parser(rs);
			}

			return bio;
		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<Skills> listarSkills() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM skills ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
		
			List<Skills> skills = new ArrayList<>();
			while (rs.next()) {
				skills.add(parserSkills(rs));
			}
			
			return skills;


		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Contact listarContact() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM contacts ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return parserContact(rs);
			}

		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return null;
	}

	public List<Work> listarWorks() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM work ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Work> works = new ArrayList<>();
			
			while (rs.next()) {
				works.add(parserWorks(rs));
			}
			return works;

		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public List<Schools> listarSchools() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM schools ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Schools> schools = new ArrayList<>();
			
			while (rs.next()) {
				schools.add(parserSchools(rs));
			}
			return schools;

		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public List<OnlineCourses> listarOcourse() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM onlinecourses ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<OnlineCourses> ocourses= new ArrayList<>();
			
			while (rs.next()) {
				ocourses.add(parserOcourse(rs));
			}
			return ocourses;

		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public List<Project> listarProjects() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM projects ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Project> projects= new ArrayList<>();
			
			while (rs.next()) {
				projects.add(parserProjects(rs));
			}
			return projects;

		} catch (SQLException e) {
			Logger.getLogger(CvController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}

	private Bio parser(ResultSet resultSet) throws SQLException {
		Bio b = new Bio();

		b.setId(resultSet.getLong("id"));
		b.setName(resultSet.getString("name"));
		b.setRole(resultSet.getString("role"));
		b.setWelcomeMessage(resultSet.getString("welcomeMessage"));
		b.setPicture(resultSet.getString("picture"));

		return b;
	}
	private Skills parserSkills(ResultSet resultSet) throws SQLException {
		Skills s = new Skills();

		s.setId(resultSet.getLong("id"));
		s.setSkill(resultSet.getString("skills"));

		return s;
	}

	private Contact parserContact(ResultSet resultSet) throws SQLException {
		Contact c = new Contact();

		c.setId(resultSet.getLong("id"));
		c.setCell(resultSet.getString("cell"));
		c.setEmail(resultSet.getString("email"));
		c.setGithubUser(resultSet.getString("githubUser"));
		c.setLocation(resultSet.getString("location"));

		return c;
	}

	private Work parserWorks(ResultSet resultSet) throws SQLException {
		Work w = new Work();

		w.setId(resultSet.getLong("id"));
		w.setTitle(resultSet.getString("title"));
		w.setEmployer(resultSet.getString("employer"));
		w.setDates(resultSet.getString("dates"));
		w.setLocation(resultSet.getString("location"));
		w.setDescription(resultSet.getString("description"));

		return w;
	}

	private Schools parserSchools(ResultSet resultSet) throws SQLException {
		Schools s = new Schools();

		s.setId(resultSet.getLong("id"));
		s.setName(resultSet.getString("name"));
		s.setCity(resultSet.getString("city"));
		s.setDegree(resultSet.getString("degree"));
		s.setMajor(resultSet.getString("major"));
		s.setGraduationYear(resultSet.getString("graduationyear"));
		s.setUrl(resultSet.getString("url"));

		return s;
	}

	private OnlineCourses parserOcourse(ResultSet resultSet) throws SQLException {
		OnlineCourses oc = new OnlineCourses();

		oc.setId(resultSet.getLong("id"));
		oc.setName(resultSet.getString("name"));
		oc.setMajor(resultSet.getString("major"));
		oc.setGraduationYear(resultSet.getString("graduationyear"));
		oc.setUrl(resultSet.getString("url"));

		return oc;
	}
	
	private Project parserProjects(ResultSet resultSet) throws SQLException {
		Project p = new Project();

		p.setId(resultSet.getLong("id"));
		p.setTitle(resultSet.getString("title"));
		p.setDates(resultSet.getString("dates"));
		p.setDescription(resultSet.getString("description"));
		p.setImagesUrl(resultSet.getString("imagesUrl"));
		p.setUrlSite(resultSet.getString("urlSite"));

		return p;
	}	

}