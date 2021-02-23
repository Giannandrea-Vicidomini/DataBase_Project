package dbutils;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;



public class QueryResult implements Iterable<QueryResult.Row>{
	
	public class Row {
		private ArrayList<Object> elements;
		
		public Row(ArrayList<Object> elements) {
			this.elements = elements;
		}
		
		private String convert(Object el, String type) {
				String res = null;
				
				if(type.equals("INT")) {
					res = Integer.toString((int)el);
				}
				else if(type.equals("VARCHAR")) {
					res = (String)el;
				}
				else if(type.equals("DECIMAL")) {
					res = ((BigDecimal)el).toString();
				}
				else if(type.equals("DATE")) {
					Date date = (Date)el;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					return sdf.format(date);
				}
				else if(type.equals("CHAR")) {
					res = (String)el;
				}
				
				return res;
		}
		
		@Override
		public String toString() {
			
			StringBuilder bld = new StringBuilder();
			
			for(int i = 0; i<elements.size(); i++) {
				
				if(i == elements.size()-1) {
					bld.append(String.format("%s(  %s  )",labels[i],convert(elements.get(i),types[i])));
				}
				else {
					bld.append(String.format("%s(  %s  )",labels[i],convert(elements.get(i),types[i])));
					bld.append("      ");
				}
			}
			
			return bld.toString();
			
		}
	}
	
	private String labels[];
	private String types[];
	private int colCount;
	private ArrayList<Row> rows;
	
	public QueryResult(ResultSet res, ResultSetMetaData resInfo) throws SQLException {
		
		rows = new ArrayList<Row>();
		this.colCount = resInfo.getColumnCount();
		
		this.types = new String[colCount];
		this.labels = new String[colCount];
		
		for(int i =0 ;i<colCount; i++) {
			types[i] = resInfo.getColumnTypeName(i+1);
			labels[i] = resInfo.getColumnLabel(i+1);
		}
		
		ArrayList<Object> objs = null;
		while(res.next()) {
			
			objs = new ArrayList<>();
			
			for(int i = 1; i<=colCount; i++) {
				objs.add(res.getObject(i));
			}
			
			rows.add(new Row(objs));
		}
	}
	
	public String[] getLabels() {
		return Arrays.copyOf(labels, labels.length);
	}
	
	public int getRowsReturned() {
		return rows.size();
	}

	@Override
	public Iterator<Row> iterator() {
		// TODO Auto-generated method stub
		return rows.iterator();
	}

}
