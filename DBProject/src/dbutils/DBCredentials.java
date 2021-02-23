package dbutils;

import java.util.TimeZone;

public class DBCredentials {
	
	private String username;
	private String password;
	private String dbName;
	private String connectionString;
	
	public DBCredentials(String username, String password, String dbName) {
		
		this.username = username;
		this.password = password;
		this.dbName = dbName;
		this.connectionString = String.format("jdbc:mysql://localhost:%s/%s?allowPublicKeyRetrieval=true&sslMode=DISABLED&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=%s","3306",dbName,TimeZone.getDefault().getID());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDbName() {
		return dbName;
	}

	public String getConnectionString() {
		return connectionString;
	}
	
	

}
