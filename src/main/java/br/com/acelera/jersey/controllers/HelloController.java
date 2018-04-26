package br.com.acelera.jersey.controllers;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aluno
 */
@Path("hello")
public class HelloController {
	//*http://localhost:8080/jersey/rest/hello/?usuario=trigao <- QueryParam
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMensagem(@QueryParam("usuario") String usuario){
        return "Bem vindo: " + usuario;
    }

    //*http://localhost:8080/jersey/rest/hello/1 <- PathParam
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("usuarios/{id}")
    public String getUsuario(@PathParam("id") long id){
        return "Recuperando usuario com ID: " + id;
    }
}