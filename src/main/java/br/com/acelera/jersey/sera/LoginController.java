package br.com.acelera.jersey.sera;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.acelera.jersey.dao.UserDAO;
import br.com.acelera.jersey.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")
public class LoginController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(User user) {
			try {
				UserDAO dao = new UserDAO();
				String created = dao.inserir(user);
				if(created == "true") {
					String KEY = "paranguaricotirrimiroaro";
					long time = System.currentTimeMillis();
					String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, KEY).setSubject("Jersey Login")
							.setIssuedAt(new Date(time)).setExpiration(new Date(time + 900000)).compact();
					JsonObject json = Json.createObjectBuilder(	).add("token", jwt).build();
					System.out.println(json);
										
					return Response.status(Response.Status.CREATED).entity(json.toString()).build();	
				}
			} catch (SQLException | ClassNotFoundException e) {
				Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}

			/*
			 * to do else {
			 * 
			 * dao.inserir(user);
			 */
			return Response.status(Response.Status.UNAUTHORIZED).build();

	};
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/")
	public Response login(String username, String password) {
			try {
				UserDAO dao = new UserDAO();
				User user = new User();
				user.setusername(username);
				user.setPassword(password);
				String created = dao.validar(user);
				if(created != null) {
					String KEY = "paranguaricotirrimiroaro";
					long time = System.currentTimeMillis();
					String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, KEY).setSubject("Jersey Login")
							.setIssuedAt(new Date(time)).setExpiration(new Date(time + 900000)).compact();
					JsonObject json = Json.createObjectBuilder(	).add("token", jwt).build();
					System.out.println(json);
										
					return Response.status(Response.Status.CREATED).entity(json.toString()).build();	
				}
			} catch (SQLException | ClassNotFoundException e) {
				Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}

			/*
			 * to do else {
			 * 
			 * dao.inserir(user);
			 */
			return Response.status(Response.Status.UNAUTHORIZED).build();

	};	
}
