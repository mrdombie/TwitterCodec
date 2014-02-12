package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	protected Connection c = null;

	public DBHelper() {
		super();
	}

	public void getConnection() throws ClassNotFoundException, SQLException{
		
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:TwitterCodec.db");
		c.setAutoCommit(false);
		
	}
	
	public void endConnection() throws SQLException{	
		c.setAutoCommit(true);
		c.close();
	}
}