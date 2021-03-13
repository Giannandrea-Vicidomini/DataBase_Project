package screens;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dbutils.DBCredentials;
import dbutils.Query;
import dbutils.QueryManager;
import dbutils.QueryResult;
import javax.swing.JTextField;

public class UpdateAccountWindow {

	private DBCredentials info;
	private JFrame frame;
	private JTextField creditField;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public UpdateAccountWindow(DBCredentials info) {
		this.info = info;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(300, 100, 450, 239);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		
		JLabel titleLabel = new JLabel("Update Account");
		titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));
		titleLabel.setForeground(new Color(30, 144, 255));
		titleLabel.setBounds(142, 6, 140, 27);
		frame.getContentPane().add(titleLabel);
		
		JLabel updateLabel = new JLabel("Accounts");
		updateLabel.setForeground(new Color(30, 144, 255));
		updateLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		updateLabel.setBounds(27, 56, 54, 16);
		frame.getContentPane().add(updateLabel);
		
		JComboBox<String> comboBox = new JComboBox<>();
		setList(comboBox);
		comboBox.setForeground(new Color(30, 144, 255));
		comboBox.setBounds(101, 52, 309, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel creditLabel = new JLabel("New credit");
		creditLabel.setForeground(new Color(30, 144, 255));
		creditLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		creditLabel.setBounds(17, 113, 64, 16);
		frame.getContentPane().add(creditLabel);
		
		creditField = new JTextField();
		creditField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		creditField.setForeground(new Color(30, 144, 255));
		creditField.setBounds(103, 107, 88, 26);
		frame.getContentPane().add(creditField);
		creditField.setColumns(10);
		
		JButton updateButton = new JButton("update");
		updateButton.setBounds(160, 160, 117, 29);
		updateButton.setForeground(new Color(30, 144, 255));
		updateButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		updateButton.addActionListener((ActionEvent e)->{
			
			String credit = creditField.getText();
			String account = comboBox.getSelectedItem().toString();
			
			if(credit.equals("") || credit==null) {
				
				JOptionPane.showMessageDialog(frame,"Enter the amout of money to update.");
				return;
			}
			else {
				String qs =String.format("update Account set credito = %s where nickname = \"%s\"",credit,account);
				var query = new Query(info,qs);
				QueryManager.handleQuery(query, null, frame);
				creditField.setText("");
				
			}
			
		});
		frame.getContentPane().add(updateButton);
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
