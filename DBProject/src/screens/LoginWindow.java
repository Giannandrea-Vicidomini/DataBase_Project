package screens;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;

import dbutils.DBConnector;
import dbutils.DBCredentials;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class LoginWindow {

	private JFrame frame;
	private int width;
	private int height;
	private int x,y;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField schemaField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		width = 600;
		height = 370;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		y = screenSize.height/2 - height/2;
		x = screenSize.width/2 - width/2;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
		lblNewLabel.setBounds(257, 25, 134, 39);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(105, 105, 105));
		textField.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		textField.setBounds(219, 98, 194, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		passwordField.setBounds(219, 142, 194, 32);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnNewButton.setForeground(new Color(30, 144, 255));
		btnNewButton.setBounds(235, 260, 143, 39);
		btnNewButton.addActionListener((ActionEvent e)->{
			String usr = textField.getText();
			String passwd = new String(passwordField.getPassword());
			String schema = schemaField.getText();
			DBCredentials info = new DBCredentials(usr, passwd,schema);
			
			try {
				DBConnector.checkConnection(info);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
				JOptionPane.showMessageDialog(frame, String.format("Error while trying to enstablish the connection...\n%s",e1.getMessage()));
				return;
				
			}
			
			List<String> tables = null;
			
			try {
				tables = DBConnector.getDbTables(info);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, String.format("Error while fetching table names...\n%s",e1.getMessage()));
				return;
			}
			
			//Open new screen
			new MainWindow(info,tables);
			frame.dispose();
			
			
		});
		
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(134, 105, 73, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setBounds(134, 149, 73, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Db schema");
		lblNewLabel_3.setForeground(new Color(30, 144, 255));
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(127, 193, 80, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		schemaField = new JTextField();
		schemaField.setForeground(new Color(105, 105, 105));
		schemaField.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		schemaField.setColumns(10);
		schemaField.setBounds(219, 186, 194, 32);
		frame.getContentPane().add(schemaField);
		
		JLabel optionbtn = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(LoginWindow.class.getResource("/resources/cogwheel.png"));
		Image image1 = imageIcon.getImage(); // transform it 
		Image newimg = image1.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		optionbtn.setIcon(imageIcon);
		optionbtn.setBounds(574, 6, 20, 25);
		optionbtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("root");
				passwordField.setText("rootngul");
				schemaField.setText("steam");
			}
		
		}); 
			
		frame.getContentPane().add(optionbtn);
		
		frame.setVisible(true);
	}
}
