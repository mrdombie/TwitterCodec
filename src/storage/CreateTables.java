package storage;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class CreateTables {

	private final static Logger LOGGER = Logger.getLogger(CreateTables.class
			.getName());
	
	private final static String profiles = "CREATE TABLE IF NOT EXISTS PROFILES "
			+ "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
			+ " NAME           TEXT    NOT NULL) ";

	private final static String tweets = "CREATE TABLE IF NOT EXISTS TWEETS "
			+ "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
			+ " PROFILE_ID 	   INT     NOT NULL,"
			+ " TWEET_ID       INT 	   NOT NULL,"
			+ " MESSAGE        TEXT    NOT NULL,"
			+ " CREATEDDATE    DATETIME  NOT NULL,"
			+ " URL    TEXT    NOT NULL,"
			+ " HASHTAGS 	   TEXT	   NULL, "
			+ " GEOLOCATION    TEXT    NOT NULL) ";
	
	private final static String poison = "CREATE TABLE IF NOT EXISTS POISON "
			+ "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
			+ " DESCRIPTION    TEXT    NOT NULL) ";
	
	public static void createTables() {

		java.sql.Connection c = null;
		Statement stmt = null;

		try {
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:TwitterCodec.db");
			
			LOGGER.info("DB CONNECTION ESTABLISHED");

			stmt = c.createStatement();
			stmt.executeUpdate(profiles);
			stmt.executeUpdate(tweets);
			stmt.executeUpdate(poison);
			
			stmt.close();
			c.close();
			
		} catch (Exception e) {
			LOGGER.warning(e.getClass().getName() + ": " + e.getMessage());
		}
			
		LOGGER.info("TABLE CREATE");
	}
	
	public static void dropTables(){
		
	}
	
	public static void main(String[] args) {
		createTables();
	}
	
}
