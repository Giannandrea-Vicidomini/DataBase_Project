package main;

import java.awt.EventQueue;
import java.sql.*;
import screens.LoginWindow;

/*
import dbutils.DBConnector;
import dbutils.DBCredentials;
import dbutils.QueryManager;
import dbutils.QueryResult;
*/

public class Main {

	public static void main(String[] args) throws SQLException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					System.out.println("Window launched  ---"+window+"---");
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		
		
		/*
		var res = QueryManager.genericQuery(new DBCredentials("root", "rootngul", "steam"), "select * from supporto");
		for(QueryResult.Row r : res) {
			System.out.println(r.toString());
		}
		*/
		
	}

}
