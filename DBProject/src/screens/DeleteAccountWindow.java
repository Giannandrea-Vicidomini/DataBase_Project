package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dbutils.DBCredentials;
import dbutils.Query;
import dbutils.QueryManager;
import dbutils.QueryResult;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class DeleteAccountWindow {

	private DBCredentials info;
	private int width;
	private int height;
	private JFrame frame;
	
	
	/*
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBCredentials info = new DBCredentials("root", "rootngul","steamDB");
					DeleteAccountWindow window = new DeleteAccountWindow(info);
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
	public DeleteAccountWindow(DBCredentials info) {
		width = 400;
		height = 239;
		this.info = info;
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(300, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel titleLabel = new JLabel("Delete Account");
		titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));
		titleLabel.setForeground(new Color(30, 144, 255));
		titleLabel.setBounds(128, 6, 140, 16);
		frame.getContentPane().add(titleLabel);
		
		JLabel deleteLabel = new JLabel("Accounts");
		deleteLabel.setForeground(new Color(30, 144, 255));
		deleteLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		deleteLabel.setBounds(27, 71, 61, 16);
		frame.getContentPane().add(deleteLabel);
		
		JComboBox<String> comboBox = new JComboBox<>();
		setList(comboBox);
		comboBox.setForeground(new Color(30, 144, 255));
		comboBox.setBounds(119, 67, 243, 27);
		frame.getContentPane().add(comboBox);
		
		JButton deleteButton = new JButton("delete");
		deleteButton.setForeground(new Color(30, 144, 255));
		deleteButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		deleteButton.setBounds(143, 141, 117, 29);
		deleteButton.addActionListener((ActionEvent e)->{
			
			String el = comboBox.getSelectedItem().toString();
			String qs = String.format("delete from Account where nickname =\"%s\" ",el);
			var query = new Query(info, qs);
			QueryManager.handleQuery(query, null, frame);
			comboBox.removeItem(el);
			
		});	
		frame.getContentPane().add(deleteButton);
		
		frame.setVisible(true);
	}
	
	private void setList(JComboBox<String> comboBox) {
		
		String[] list = getAccountList();
		comboBox.setModel(new DefaultComboBoxModel<String>(list));
		comboBox.setSelectedIndex(0);
		
	}
	
	private String[] getAccountList() {
		String qs = "select nickname from Account";
		QueryResult res = null;
		try {
			res = QueryManager.genericQuery(info, qs);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame,e.getMessage());
			System.exit(1);
		}
		return res.getColumn(0);
	}
}
