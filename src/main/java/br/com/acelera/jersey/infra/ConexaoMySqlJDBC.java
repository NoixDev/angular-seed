package br.com.acelera.jersey.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMySqlJDBC implements ConexaoJDBC {
	// *Class.forName("com.mysql.jdbc.Driver");

	private Connection con = null;
	
	public ConexaoMySqlJDBC() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Properties properties = new Properties();
		properties.put("user", "root");
		properties.put("password", "root");
		
		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?ApplicationName=Duvidas", properties);
		this.con.setAutoCommit(false);
	}
	
	@Override
	public Connection getConnection() {
		return this.con;
	}

	@Override
	public void close() {
		if (this.con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoMySqlJDBC.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	@Override
	public void commit() throws SQLException {
		this.con.commit();
		this.close();
	}

	@Override
	public void rollback() {
		if (this.con != null) {
			try {
				this.con.rollback();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoMySqlJDBC.class.getName()).log(Level.SEVERE, null, e);
			} finally {
				this.close();
			}
		}

	}

}
