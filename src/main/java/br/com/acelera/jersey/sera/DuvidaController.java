package br.com.acelera.jersey.sera;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.acelera.jersey.dao.DuvidasDAO;
import br.com.acelera.jersey.models.Duvida;
import br.com.acelera.jersey.models.Status;

@Path("duvidas/")
public class DuvidaController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Duvida> listDuvidas() {
		try {
			DuvidasDAO dao = new DuvidasDAO();
			return dao.listar();

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Duvida getDuvida(@PathParam("id") long id) {
		try {
			DuvidasDAO dao = new DuvidasDAO();
			Duvida d1 = new Duvida();
			d1.setId(id);
			d1 = dao.selecionar(d1.getId());

			return d1;

		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Duvida duvida) {
		try {
			duvida.setStatus(Status.NOVO);
			DuvidasDAO dao = new DuvidasDAO();
			int id = 0;
			id = dao.inserir(duvida);
			System.out.println(id);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Duvida duvida) {
		try {
			System.out.println(duvida.toString());
			DuvidasDAO dao = new DuvidasDAO();
			duvida.setStatus(Status.PENDENTE);
			dao.alterar(duvida);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response concluir(@PathParam("id") int id) {
		try {
			DuvidasDAO dao = new DuvidasDAO();
			Duvida d = new Duvida();
			d = dao.selecionar(id);
			d.setStatus(Status.FECHADO);
			dao.alterar(d);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	};

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response delete(@PathParam("id") int id) {
		System.out.println("Deletando ID: " + id);
		try
		{
			DuvidasDAO dao = new DuvidasDAO();
			int linhas = dao.excluir(id);
			System.out.println(linhas);
		} catch (SQLException | ClassNotFoundException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.status(Response.Status.OK).build();
		
	};

}
