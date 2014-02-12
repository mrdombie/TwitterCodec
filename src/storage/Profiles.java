package storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Profiles extends DBHelper {
	
	private static final String PROFILEINSERTSQL ="INSERT INTO PROFILES(NAME) VALUES (?)";
	private Statement stmt = null;

	public void addProfiles() throws SQLException, ClassNotFoundException {

		getConnection();

		stmt = c.createStatement();

		PreparedStatement statement = c.prepareStatement(PROFILEINSERTSQL);
		statement.setString(1, "PROFILE ONE");
		statement.executeUpdate();
		
		c.commit();
		stmt.close();
		endConnection();

	}

}
