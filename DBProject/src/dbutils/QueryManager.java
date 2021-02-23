package dbutils;

import java.sql.*;

public class QueryManager {
	
	private QueryManager() {}
	
	public static QueryResult genericQuery(DBCredentials info,String query)throws SQLException {
		
		DBConnector connector = new DBConnector(info);
		Connection conn = connector.getConnection();
		
		PreparedStatement stm = conn.prepareStatement(query);
		
		ResultSet res = null;
		try {
			res = stm.executeQuery();
		}
		catch(SQLException e) {
			connector.closeConnection();
			throw e;
		}
		
		ResultSetMetaData resInfo = res.getMetaData();
		
		QueryResult rows = new QueryResult(res,resInfo);
		
		int sts = connector.closeConnection();
		if(sts == -1) throw new SQLException("Somehting went wrong closing the database.");
		
		return rows;
	}
	
	public static int genericUpdate(DBCredentials info,String query)throws SQLException {
		DBConnector connector = new DBConnector(info);
		Connection conn = connector.getConnection();
		PreparedStatement stm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		int affectedRows = 0;
		
		try {
			affectedRows = stm.executeUpdate();
		}
		catch(SQLException e) {
			connector.closeConnection();
			throw e;
		}
		
		connector.closeConnection();
		
		return affectedRows;
		
	}
	

}
