package dbutils;

import java.sql.SQLException;

public class Query {
	
	private static final String INDICATOR = "Can not issue data manipulation statements with executeQuery().";
	private String query;
	private DBCredentials info;
	
	public Query(DBCredentials info, String query) {
		this.query = query;
		this.info = info;
	}
	
	public Object execute() throws SQLException{
		
		QueryResult res = null;
		
		try{
			res = QueryManager.genericQuery(info, query);
		}
		catch(SQLException e) {
			if(e.getMessage().equals(INDICATOR)) {
				
				return QueryManager.genericUpdate(info, query);
				
			}
			else {
				throw e;
			}
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return query;
	}

}
