package storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Profiles extends DBHelper {
	
	private static final String PROFILEINSERTSQL ="INSERT INTO TWEETS(PROFILE_ID, DESCRIPTION) VALUES (?,?)";
	private Statement stmt = null;

	public void createProfiles() throws SQLException, ClassNotFoundException {

		getConnection();

		stmt = c.createStatement();

		PreparedStatement statement = c.prepareStatement(PROFILEINSERTSQL);
		statement.setInt(1, 0);
		statement.setString(2, "DESCRIPTION");
	
		statement.executeUpdate();
		
		c.commit();
		stmt.close();
		endConnection();

	}

}
