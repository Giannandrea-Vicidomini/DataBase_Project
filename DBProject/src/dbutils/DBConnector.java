package dbutils;

import java.sql.*;
import java.util.ArrayList;



public class DBConnector {
	
	private DBCredentials credentials;
	private Connection dbConnection = null;
		
	
	public DBConnector(DBCredentials credentials) {
		
		this.credentials = credentials;
	}
	
	public int closeConnection() {
		if(dbConnection != null) {
			try {
				dbConnection.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	
	public Connection getConnection() {
		if(dbConnection == null) {
			dbConnection = connect();
		}
		return dbConnection;
	}

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(credentials.getConnectionString(),credentials.getUsername(),credentials.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return conn;
	}
	
	public static boolean checkConnection(DBCredentials credentials) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(credentials.getConnectionString(),credentials.getUsername(),credentials.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return true;
	}
	
	public static ArrayList<String> getDbTables(DBCredentials credentials) throws SQLException{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(credentials.getConnectionString(),credentials.getUsername(),credentials.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		ArrayList<String> tables = extractTables(conn);
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return tables;
	}
	
	private static ArrayList<String> extractTables(Connection conn) throws SQLException{
		var metaData = conn.getMetaData();
		var rs = metaData.getTables(null,null,null,new String[] {"TABLE"});
		
		var res = new ArrayList<String>();
		while(rs.next()) {
			res.add(rs.getString("TABLE_NAME"));
		}
		
		return res;
	}

}
