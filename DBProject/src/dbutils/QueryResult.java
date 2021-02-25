package dbutils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JTable;

import javax.swing.border.EtchedBorder;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



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
		
		public String get(int pos) {
			return convert(elements.get(pos),types[pos]);
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
	
	public int getColumnCount() {
		return labels.length;
	}
	
	public String getElement(int row, int column) {
		return rows.get(row).get(column);
	}

	@Override
	public Iterator<Row> iterator() {
		// TODO Auto-generated method stub
		return rows.iterator();
	}
	
	public String[][] toStringMatrix(){
		int rows = getRowsReturned();
		int columns = getColumnCount();
		
		String[][] table = new String[rows][columns];
		
		for(int i=0; i<rows ;i++) {
			for(int j = 0; j<columns; j++) {
				table[i][j] = getElement(i, j);
			}
		}
		
		return table;
	}
	
	public JTable getResultTable() {
		
		@SuppressWarnings("serial")
		JTable table = new JTable(toStringMatrix(),labels){
		    @Override
		       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		           Component component = super.prepareRenderer(renderer, row, column);
		           int rendererWidth = component.getPreferredSize().width;
		           TableColumn tableColumn = getColumnModel().getColumn(column);
		           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		           return component;
		        }
		    };
		
		
		    //JTable table = new JTable(toStringMatrix(),labels);
		table.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		table.setRowHeight(55);
		table.setForeground(new Color(10, 124, 235));
		table.setShowGrid(true);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		table.setGridColor(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JTableHeader tableHeader = table.getTableHeader();
	    tableHeader.setBackground(new Color(30, 144, 255));
		tableHeader.setOpaque(false);
	    tableHeader.setForeground(Color.white);
	    tableHeader.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
	    
	    
		return table;
	}

}
