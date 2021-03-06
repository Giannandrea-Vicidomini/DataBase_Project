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
        
        if(dbInfo.getDbName().toLowerCase().equals("steamdb")) {
        	
        	queryMenu = new JMenu("Default Queries");
            
             
        	JMenuItem addQueryMenu = new JMenuItem("Add Account");
        	addQueryMenu.setForeground(new Color(30, 144, 255));
        	addQueryMenu.setBackground(new Color(255, 255, 255));
        	addQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	addQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new AddAccountWindow(dbInfo);
        	
            });
        	
        	queryMenu.add(addQueryMenu);
            
        	JMenuItem deleteQueryMenu = new JMenuItem("Delete Account");
        	deleteQueryMenu.setForeground(new Color(30, 144, 255));
        	deleteQueryMenu.setBackground(new Color(255, 255, 255));
        	deleteQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	deleteQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new DeleteAccountWindow(dbInfo);
        	
            });
        	
        	queryMenu.add(deleteQueryMenu);
        	
        	JMenuItem updateQueryMenu = new JMenuItem("update Account");
        	updateQueryMenu.setForeground(new Color(30, 144, 255));
        	updateQueryMenu.setBackground(new Color(255, 255, 255));
        	updateQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	updateQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new UpdateAccountWindow(dbInfo);
        	
        		
            });
        	queryMenu.add(updateQueryMenu);
        	
        	JMenuItem searchQueryMenu = new JMenuItem("Search Horror games");
        	searchQueryMenu.setForeground(new Color(30, 144, 255));
        	searchQueryMenu.setBackground(new Color(255, 255, 255));
        	searchQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	searchQueryMenu.addActionListener((ActionEvent e)->{
        		
        		String qs = "select Gioco.id_gioco, titolo, nomegenere, datarilascio, Azienda.nome "
        				+ "from (((Gioco join Genere on Gioco.id_gioco = Genere.id_gioco) "
        				+ "join Produzione on Gioco.id_gioco = Produzione.idg) "
        				+ "join Azienda on Produzione.piva = Azienda.partitaiva) "
        				+ "where nomegenere = \"horror\"";
        		searchField.setText(qs);
        	
            });
        	
        	queryMenu.add(searchQueryMenu);
        	
        	JMenuItem searchNAAQueryMenu = new JMenuItem("Search NAA");
        	searchNAAQueryMenu.setForeground(new Color(30, 144, 255));
        	searchNAAQueryMenu.setBackground(new Color(255, 255, 255));
        	searchNAAQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	searchNAAQueryMenu.addActionListener((ActionEvent e)->{
        		
        		String qs = "select D.nome as Nome , A.id_assistente as Assistente, count(*) as NAA "
        				+ "from Dipendente D join Assistente A on D.id_dipendente = A.id_assistente "
        				+ "join Supporto S on A.id_assistente = S.assistente "
        				+ "group by A.id_assistente";
        		
        		searchField.setText(qs);
        	
            });
        	
        	queryMenu.add(searchNAAQueryMenu);
        	
        	JMenuItem searchNGAQueryMenu = new JMenuItem("Search NGA");
        	searchNGAQueryMenu.setForeground(new Color(30, 144, 255));
        	searchNGAQueryMenu.setBackground(new Color(255, 255, 255));
        	searchNGAQueryMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        	searchNGAQueryMenu.addActionListener((ActionEvent e)->{
        		
        		
        		String qs = "select A.nickname as Account, count(A.nickname) as NGA "
        				+ "from Account A join Acquisto Q on A.nickname = Q.username "
        				+ "join Gioco G on Q.idg = G.id_gioco "
        				+ "group by A.nickname";
        		
        		searchField.setText(qs);
        	
            });
        	
        	queryMenu.add(searchNGAQueryMenu);
        
        	
        	mb.add(queryMenu);
        }
        
        JMenu menu1 = new JMenu("Help");
        if(dbInfo.getDbName().toLowerCase().equals("steamdb")) {
        	
        	JMenuItem schemaMenu = new JMenuItem("Schema");
            schemaMenu.setForeground(new Color(30, 144, 255));
            schemaMenu.setBackground(new Color(255, 255, 255));
            schemaMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
            schemaMenu.addActionListener((ActionEvent e)->{
        		
        		
        		new SchemaWindow();
        	
        		
            });
        	
        	menu1.add(schemaMenu);
        }	
        
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
		searchField.setBounds(68, 244, 745, 58);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		//BUTTON THAT PERFORMS SIMPLE QUERY
		searchButton = new JButton("RUN QUERY");
		searchButton.setBackground(new Color(30, 144, 255));
		searchButton.setForeground(new Color(30, 144, 255));
		searchButton.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
		searchButton.setBounds(820, 245, 120, 56);
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
