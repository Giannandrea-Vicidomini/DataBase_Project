package dbutils;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
	
	public static void handleQuery(Query q, JScrollPane target, JFrame frame) {
		Object result = null;
		target.setViewportView(null);
		
		try {
			result = q.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame, e1.getMessage());
			return;
			
		}
		
		if(result.getClass() == QueryResult.class) {
			QueryResult finalResult = (QueryResult)result;
			JOptionPane.showMessageDialog(frame,String.format("The query was successful!\n%d row(s) returned.",finalResult.getRowsReturned()));
			
			var table = finalResult.getResultTable();
			target.setViewportView(table);
			table.validate();
			
		}
		else {
			JOptionPane.showMessageDialog(frame,String.format("The update was successful!\n%d row(s) affected.",(Integer)result));
		}
		
	}

}
