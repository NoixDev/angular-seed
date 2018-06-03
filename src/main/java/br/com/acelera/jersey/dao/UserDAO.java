package br.com.acelera.jersey.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.acelera.jersey.infra.ConexaoJDBC;
import br.com.acelera.jersey.infra.ConexaoMySqlJDBC;
import br.com.acelera.jersey.models.User;
import br.com.acelera.jersey.sera.DuvidaController;

public class UserDAO {

	private final ConexaoJDBC conexao;
	private final String auth = "false";

	public UserDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMySqlJDBC();
	}

	public String inserir(User user) throws SQLException, ClassNotFoundException {
		String sucesso = "false";
		String sqlQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, user.getusername().toString());
			stmt.setString(2, user.getPassword());	
			
			stmt.executeUpdate();
			this.conexao.commit();
			sucesso = "true";
		} catch (SQLException e) {
			this.conexao.rollback();
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return sucesso;
	}
	
	public String validar(User user) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM users WHERE name = ? and password = ?";
		String token = null;
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, user.getusername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				token = rs.getString("token");
				
				return token;
			}
		} catch (SQLException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return token;
	}
/*	private User parser(ResultSet resultSet) throws SQLException {
		User u = new User();

		u.setId(resultSet.getLong("id"));
		u.setusername(resultSet.getString("username"));
		u.setPassword(resultSet.getString("password"));
		return u;
	}*/
	
	public int returnLastId() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT LAST_INSERT_ID() INTO @ID FROM users;";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			int id = rs.getInt(1);
			return id;
		} catch (SQLException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}