package screens;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import dbutils.DBCredentials;
import dbutils.Query;
import dbutils.QueryManager;



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
	
	private Random rng;
	private List<String> tables;
	private DBCredentials dbInfo;
	private int x,y;
	private static final int width = 950;
	private static final int height = 670;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JLabel searchQuery;
	private JTextField searchField;
	private JButton searchButton;
	private JSeparator separator_1;
	private JPanel banner;
	private JLabel bannerLogo;
	private JMenu queryMenu;
	private JMenu optionMenu;
	private JMenuItem logOutItem;
	private ActionListener retrieveQueryMethod;
	private JMenuItem batchQueryitem;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DBCredentials info = new DBCredentials("root", "rootngul","steamDB");
					
					String[] elems = {"Dio","cane"};
					MainWindow window = new MainWindow(info,Arrays.asList(elems));
					
					System.out.println("MainWindow running... ----"+window.toString()+"----");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	*/
	/**
	 * Create the application.
	 */
	public MainWindow(DBCredentials dbInfo, List<String> dbtables) {
		
		this.tables = dbtables;
		this.rng = new Random();
		
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
		
		
		retrieveQueryMethod = (ActionEvent e)->{
			
			//DO QUERY
			
			String query = searchField.getText();
			if(query == null || query.equals("")) {
				JOptionPane.showMessageDialog(frame, "The field is empty...");
				return;
			}
			
			Query q = new Query(dbInfo,query);
			
			QueryManager.handleQuery(q, scrollPane, frame);
			searchField.setText("");
			
		};
		
		
		
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
        frame.setJMenuBar(mb);
        
        if(dbInfo.getDbName().equals("steamDB")) {
        	
        	queryMenu = new JMenu("Default Queries");
            /*
             * 
             * 
             * 
             *     ADDING QUERIES HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * 
             * 
             */
        	JMenuItem updateQueryMenu = new JMenuItem("Add Account");
        	updateQueryMenu.setForeground(new Color(30, 144, 255));
        	updateQueryMenu.setBackground(new Color(255, 255, 255));
        	updateQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	updateQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new AddAccountWindow(dbInfo);
        	
            });
        	
        	queryMenu.add(updateQueryMenu);
            
        	JMenuItem deleteQueryMenu = new JMenuItem("Delete Account");
        	deleteQueryMenu.setForeground(new Color(30, 144, 255));
        	deleteQueryMenu.setBackground(new Color(255, 255, 255));
        	deleteQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	deleteQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new DeleteAccountWindow(dbInfo);
        	
            });
        	
        	queryMenu.add(deleteQueryMenu);
        	
        	
        	mb.add(queryMenu);
        }
        
        JMenu menu1 = new JMenu("Help");
        
        
        JMenu tables =new JMenu("Tables");
        tables.setForeground(new Color(30, 144, 255));
        tables.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        createTablesItems(tables);
        
        optionMenu = new JMenu("Options");
        mb.add(optionMenu);
        
        logOutItem = new JMenuItem("log out");
        logOutItem.setForeground(new Color(30, 144, 255));
        logOutItem.setBackground(new Color(255, 255, 255));
        logOutItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        logOutItem.addActionListener((ActionEvent e)->{
        	new LoginWindow();
        	frame.dispose();
        });
        
        batchQueryitem = new JMenuItem("batch queries");
        batchQueryitem.setForeground(new Color(30, 144, 255));
        batchQueryitem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        batchQueryitem.addActionListener((ActionEvent e)->{
        	
        	new BatchQueryWindow(dbInfo, scrollPane);
        });
        optionMenu.add(batchQueryitem);
        optionMenu.add(logOutItem);
        
        menu1.add(tables);
        
        mb.add(menu1);
		//frame.getContentPane().add(textArea);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 342, 930, 277);
		frame.getContentPane().add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 324, 930, 12);
		frame.getContentPane().add(separator);
		
		searchQuery = new JLabel("SQL");
		searchQuery.setForeground(new Color(30, 144, 255));
		searchQuery.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		searchQuery.setBounds(10, 257, 77, 28);
		searchQuery.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = rng.nextInt(MainWindow.this.tables.size());
				searchField.setText(String.format("select * from %s",MainWindow.this.tables.get(index)));
			}
			
		});
		frame.getContentPane().add(searchQuery);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		searchField.setForeground(new Color(30, 144, 255));
		searchField.setBounds(68, 257, 745, 29);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		//BUTTON THAT PERFORMS SIMPLE QUERY
		searchButton = new JButton("query");
		searchButton.setBackground(new Color(30, 144, 255));
		searchButton.setForeground(new Color(30, 144, 255));
		searchButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		searchButton.setBounds(823, 256, 117, 29);
		searchButton.addActionListener(retrieveQueryMethod);
		frame.getContentPane().add(searchButton);
		
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
		bannerLogo.setBounds(288, 6, 631, 187);
		banner.add(bannerLogo);
		
		frame.setVisible(true);
	}


	private void createTablesItems(JMenu tablesMenu) {
		JMenuItem temp = null;
		for(var table : this.tables) {
			temp = new JMenuItem(table);
			temp.setForeground(new Color(30, 144, 255));
	        temp.setBackground(new Color(255, 255, 255));
	        temp.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
	        temp.addActionListener((ActionEvent e)->{
	        	String q = String.format("select * from %s",table);
	        	searchField.setText(q);
	        });
	        
	        tablesMenu.add(temp);
		}
		
	}
	
	
}
