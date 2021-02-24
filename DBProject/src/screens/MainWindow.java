package screens;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import dbutils.DBCredentials;
import dbutils.QueryManager;
import dbutils.QueryResult;



import javax.swing.JSeparator;
import java.awt.Font;

import java.awt.Image;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MainWindow {
	
	private List<String> tables;
	private DBCredentials dbInfo;
	private int x,y;
	private static final int width = 950;
	private static final int height = 670;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JLabel updateQuery;
	private JLabel searchQuery;
	private JTextField searchField;
	private JTextField updateField;
	private JButton searchButton;
	private JButton updateButton;
	private JSeparator separator_1;
	private JPanel banner;
	private JLabel bannerLogo;
	private JMenu queryMenu;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DBCredentials info = new DBCredentials("root", "rootngul","steam");
					
					String[] elems = {"Dio","cane"};
					MainWindow window = new MainWindow(info,Arrays.asList(elems));
					
					System.out.println("MainWindow running... ----"+window.toString()+"----");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public MainWindow(DBCredentials dbInfo, List<String> dbtables) {
		
		this.tables = dbtables;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int y = screenSize.height/2 - height/2;
		int x = screenSize.width/2 - width/2;
		this.dbInfo = dbInfo;
		
		this.x = x;
		this.y = y;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(String.format("%s WorkBench",dbInfo.getDbName().toUpperCase()));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JMenuBar mb = new JMenuBar();
		mb.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		mb.setForeground(new Color(30, 144, 255));
		
        JMenu menu1 = new JMenu("Help");
        
        JMenuItem tableInfo = new JMenuItem("DB Info");
        tableInfo.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        tableInfo.setForeground(new Color(30, 144, 255));
        tableInfo.addActionListener((ActionEvent e)->{
        	
        	String message = buildHelpMessage();
        	
        	JOptionPane.showMessageDialog(frame,message);
        });
        
        menu1.add(tableInfo);
        mb.add(menu1);
        frame.setJMenuBar(mb);
        
        queryMenu = new JMenu("Default Queries");
        /*
         * 
         * 
         * 
         *     ADDING QUERIES HERE
         * 
         * 
         */
        mb.add(queryMenu);
		//frame.getContentPane().add(textArea);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 358, 930, 261);
		frame.getContentPane().add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 341, 930, 12);
		frame.getContentPane().add(separator);
		
		updateQuery = new JLabel("Update");
		updateQuery.setForeground(new Color(30, 144, 255));
		updateQuery.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		updateQuery.setBounds(10, 290, 77, 28);
		frame.getContentPane().add(updateQuery);
		
		searchQuery = new JLabel("SQL");
		searchQuery.setForeground(new Color(30, 144, 255));
		searchQuery.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		searchQuery.setBounds(10, 240, 77, 28);
		searchQuery.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("select * from account");
			}
			
		});
		frame.getContentPane().add(searchQuery);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		searchField.setForeground(new Color(30, 144, 255));
		searchField.setBounds(101, 240, 714, 29);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		updateField = new JTextField();
		updateField.setForeground(new Color(30, 144, 255));
		updateField.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		updateField.setColumns(10);
		updateField.setBounds(101, 290, 714, 29);
		frame.getContentPane().add(updateField);
		
		//BUTTON THAT PERFORMS SIMPLE QUERY
		searchButton = new JButton("query");
		searchButton.setBackground(new Color(30, 144, 255));
		searchButton.setForeground(new Color(30, 144, 255));
		searchButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		searchButton.setBounds(823, 239, 117, 29);
		searchButton.addActionListener((ActionEvent e)->{
			
			//DO QUERY
			QueryResult result = null;
			scrollPane.setViewportView(null);
			
			String query = searchField.getText();
			if(query == null || query.equals("")) {
				JOptionPane.showMessageDialog(frame, "The field is empty...");
				return;
			}
			
			try {
				result = QueryManager.genericQuery(dbInfo, query);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame, e1.getMessage());
				searchField.setText("");
				return;
				
			}
			
			JOptionPane.showMessageDialog(frame,String.format("The query was successful!\n%d row(s) returned.",result.getRowsReturned()));
			/*
			for(QueryResult.Row row : result) {
				
				textArea.append(row.toString()+"\n\n");
			}
			*/
			var table = result.getTable();
			scrollPane.setViewportView(table);
			table.validate();
			
			searchField.setText("");
			
		});
		frame.getContentPane().add(searchButton);
		
		//BUTTON THAT PERFROMS UPDATES
		updateButton = new JButton("execute");
		updateButton.setForeground(new Color(30, 144, 255));
		updateButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		updateButton.setBackground(new Color(30, 144, 255));
		updateButton.setBounds(823, 290, 117, 29);
		updateButton.addActionListener((ActionEvent e)->{
			
			//DO UPDATE
			int affectedRows = 0;
			scrollPane.setViewportView(null);

			String query = updateField.getText();
			if(query == null || query.equals("")) {
				JOptionPane.showMessageDialog(frame, "The field is empty...");
				return;
			}
			
			try {
				affectedRows = QueryManager.genericUpdate(dbInfo,query);
			}
			catch(SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame, e1.getMessage());
				updateField.setText("");
				return;
			}
			
			JOptionPane.showMessageDialog(frame,String.format("The update was successful!\n%d row(s) affected.",affectedRows));
			updateField.setText("");
			
		});
		frame.getContentPane().add(updateButton);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 209, 930, 12);
		frame.getContentPane().add(separator_1);
		
		banner = new JPanel();
		banner.setBackground(new Color(30, 144, 255));
		banner.setBounds(10, 6, 930, 200);
		frame.getContentPane().add(banner);
		banner.setLayout(null);
		
		JLabel SQLogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(MainWindow.class.getResource("/resources/sql.png"));
		Image image1 = imageIcon.getImage(); // transform it 
		Image newimg = image1.getScaledInstance(250, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg); 
		
		SQLogo.setIcon(imageIcon);
		SQLogo.setBounds(6, 6, 270, 188);
		//SQLogo.setIcon(new ImageIcon(getClass().getResource("/resource/sql.png")));
		banner.add(SQLogo);
		
		bannerLogo = new JLabel("");
		ImageIcon banneric= new ImageIcon(MainWindow.class.getResource("/resources/DB-Sql-Banner.png"));
		Image image2 = banneric.getImage(); // transform it 
		Image newimg1 = image2.getScaledInstance(650, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		banneric = new ImageIcon(newimg1);
		bannerLogo.setIcon(banneric);
		bannerLogo.setBounds(288, 6, 636, 188);
		banner.add(bannerLogo);
		
		frame.setVisible(true);
	}


	private String buildHelpMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("This database has the following tables:\n");
		for(String table : tables) {
			String token = String.format("- %s\n", table);
			sb.append(token);
		}
		return sb.toString();
	}
	
	
}
