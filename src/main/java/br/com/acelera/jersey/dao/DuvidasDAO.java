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

import br.com.acelera.jersey.infra.ConexaoJDBC;
import br.com.acelera.jersey.infra.ConexaoMySqlJDBC;
import br.com.acelera.jersey.models.Duvida;
import br.com.acelera.jersey.models.Status;
import br.com.acelera.jersey.sera.DuvidaController;

public class DuvidasDAO {

	private final ConexaoJDBC conexao;

	public DuvidasDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMySqlJDBC();
	}

	public int inserir(Duvida duvida) throws SQLException, ClassNotFoundException {
		int id = 0;
		String sqlQuery = "INSERT INTO duvidas (status, mensagem, assunto) VALUES (?, ?, ?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, duvida.getStatus().toString());
			stmt.setString(2, duvida.getMensagem());
			stmt.setString(3, duvida.getAssunto());
			
			stmt.executeUpdate();
			id = this.returnLastId();
			System.out.println(id);
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return id;
	}

	public int alterar(Duvida duvida) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE duvidas SET assunto = ?, status = ?, mensagem = ? WHERE id = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, duvida.getAssunto());
			stmt.setString(2, duvida.getStatus().toString());
			stmt.setString(3, duvida.getMensagem());
			stmt.setLong(4, duvida.getId());

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);	}

		return linhasAfetadas;
	}

	public int excluir(long id) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM duvidas WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return linhasAlfetadas;
	}

	public Duvida selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM duvidas WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}
		} catch (SQLException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	public List<Duvida> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM duvidas ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Duvida> duvidas = new ArrayList<>();

			while (rs.next()) {
				duvidas.add(parser(rs));
			}

			return duvidas;
		} catch (SQLException e) {
			Logger.getLogger(DuvidaController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	private Duvida parser(ResultSet resultSet) throws SQLException {
		Duvida d = new Duvida();

		d.setId(resultSet.getLong("id"));
		d.setAssunto(resultSet.getString("assunto"));
		d.setMensagem(resultSet.getString("mensagem"));
		d.setStatus(Status.valueOf(resultSet.getString("status")));

		return d;
	}

	public int returnLastId() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT LAST_INSERT_ID() INTO @ID;";
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