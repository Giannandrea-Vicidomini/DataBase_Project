package screens;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import dbutils.DBCredentials;
import dbutils.Query;
import dbutils.QueryManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddAccountWindow {

	private JFrame frame;
	private int width;
	private int height;
	private JTextField nickNameField;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField nationField;
	private JTextField regionField;
	private JTextField cityField;
	private JTextField creditField;
	private DBCredentials info;
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBCredentials info = new DBCredentials("root", "rootngul","steamDB");
					AddAccountWindow window = new AddAccountWindow(info);
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
	public AddAccountWindow(DBCredentials info) {
		
		this.info = info;
		width = 350;
		height = 560;
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(300, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Add Account");
		titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));
		titleLabel.setForeground(new Color(30, 144, 255));
		titleLabel.setBounds(119, 6, 120, 16);
		frame.getContentPane().add(titleLabel);
		
		JLabel nicknameLable = new JLabel("Nickname");
		nicknameLable.setForeground(new Color(30, 144, 255));
		nicknameLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		nicknameLable.setBounds(26, 64, 67, 16);
		frame.getContentPane().add(nicknameLable);
		
		JLabel emailLable = new JLabel("Email");
		emailLable.setForeground(new Color(30, 144, 255));
		emailLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		emailLable.setBounds(57, 122, 36, 16);
		frame.getContentPane().add(emailLable);
		
		JLabel passwordLable = new JLabel("Password");
		passwordLable.setForeground(new Color(30, 144, 255));
		passwordLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		passwordLable.setBounds(25, 180, 68, 16);
		frame.getContentPane().add(passwordLable);
		
		JLabel nationLable = new JLabel("Nation");
		nationLable.setForeground(new Color(30, 144, 255));
		nationLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		nationLable.setBounds(49, 243, 44, 16);
		frame.getContentPane().add(nationLable);
		
		JLabel regionLable = new JLabel("Region");
		regionLable.setForeground(new Color(30, 144, 255));
		regionLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		regionLable.setBounds(46, 310, 47, 16);
		frame.getContentPane().add(regionLable);
		
		JLabel cityLable = new JLabel("City");
		cityLable.setForeground(new Color(30, 144, 255));
		cityLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		cityLable.setBounds(66, 374, 27, 16);
		frame.getContentPane().add(cityLable);
		
		JLabel creditLable = new JLabel("Credit");
		creditLable.setForeground(new Color(30, 144, 255));
		creditLable.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		creditLable.setBounds(49, 444, 44, 16);
		frame.getContentPane().add(creditLable);
		
		nickNameField = new JTextField();
		nickNameField.setForeground(new Color(30, 144, 255));
		nickNameField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		nickNameField.setBounds(119, 60, 198, 26);
		frame.getContentPane().add(nickNameField);
		nickNameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setForeground(new Color(30, 144, 255));
		emailField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		emailField.setColumns(10);
		emailField.setBounds(119, 118, 198, 26);
		frame.getContentPane().add(emailField);
		
		passwordField = new JTextField();
		passwordField.setForeground(new Color(30, 144, 255));
		passwordField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		passwordField.setColumns(10);
		passwordField.setBounds(119, 176, 198, 26);
		frame.getContentPane().add(passwordField);
		
		nationField = new JTextField();
		nationField.setForeground(new Color(30, 144, 255));
		nationField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		nationField.setColumns(10);
		nationField.setBounds(119, 239, 198, 26);
		frame.getContentPane().add(nationField);
		
		regionField = new JTextField();
		regionField.setForeground(new Color(30, 144, 255));
		regionField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		regionField.setColumns(10);
		regionField.setBounds(119, 306, 198, 26);
		frame.getContentPane().add(regionField);
		
		cityField = new JTextField();
		cityField.setForeground(new Color(30, 144, 255));
		cityField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		cityField.setColumns(10);
		cityField.setBounds(119, 370, 198, 26);
		frame.getContentPane().add(cityField);
		
		creditField = new JTextField();
		creditField.setForeground(new Color(30, 144, 255));
		creditField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		creditField.setColumns(10);
		creditField.setBounds(119, 440, 198, 26);
		frame.getContentPane().add(creditField);
		
		JButton AddButton = new JButton("Add account");
		AddButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		AddButton.setForeground(new Color(30, 144, 255));
		AddButton.setBounds(119, 491, 117, 29);
		AddButton.addActionListener((ActionEvent e)->{
			
			var res = checkInput(nickNameField,emailField,passwordField,nationField,regionField,cityField,creditField);
			
			if(res == false) {
				JOptionPane.showMessageDialog(frame,"Make sure you fill all the fields before submitting");
				return;
			}
			
			String queryString = prepareQueryString(nickNameField,emailField,passwordField,nationField,regionField,cityField,creditField);
			
			var query = new Query(info,queryString); 
			QueryManager.handleQuery(query, null, frame);
			nickNameField.setText("");
			emailField.setText("");
			passwordField.setText("");
			nationField.setText("");
			regionField.setText("");
			cityField.setText("");
			creditField.setText("");
			
			
			
		});
		frame.getContentPane().add(AddButton);
		
		JLabel optionbtn = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(AddAccountWindow.class.getResource("/resources/cogwheel.png"));
		Image image1 = imageIcon.getImage(); // transform it 
		Image newimg = image1.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		optionbtn.setIcon(imageIcon);
		optionbtn.setBounds(312, 6, 20, 25);
		optionbtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				nickNameField.setText("Adonai");
				emailField.setText("adonai@gmail.com");
				passwordField.setText("Adonai");
				nationField.setText("Italia");
				regionField.setText("Campania");
				cityField.setText("Nocera");
				creditField.setText("34.80");
			}
		
		});
		frame.getContentPane().add(optionbtn);
		frame.setVisible(true);
	}
	
	private boolean checkInput(JTextField... fields) {
		for(var field : fields) {
			String txt = field.getText();
			if(txt.equals("") || txt == null) {
				return false;
			}
		}
		return true;
	}
	
	private String prepareQueryString(JTextField... fields) {
		ArrayList<String> elements = Arrays.stream(fields)
				.map((JTextField el) -> el.getText())
				.collect(Collectors.toCollection(ArrayList<String>::new));
		
		String query = String.format("insert into Account values(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%s)",elements.toArray(new Object[elements.size()]));
		
		return query;
		
	}
}
