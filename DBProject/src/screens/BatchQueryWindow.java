package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import dbutils.DBCredentials;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dbutils.Query;
import dbutils.QueryManager;


import java.awt.Font;


public class BatchQueryWindow {

	private DBCredentials info;
	private JFrame frame;
	private JTextField fileTextField;
	private File chosenFile;
	private JList<Query> items;
	private JScrollPane scrollPane;
	private ArrayList<Query> qList;
	private JButton loadButton;
	private JScrollPane target;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public BatchQueryWindow(DBCredentials info,JScrollPane target) {
		this.target = target;
		this.info = info;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ActionListener chooseFile = (ActionEvent e)->{
			
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fc.showOpenDialog(frame);
			
			if (result == JFileChooser.APPROVE_OPTION) {
			    // user selects a file
				chosenFile = fc.getSelectedFile();
				fileTextField.setText(chosenFile.getPath());
			
			}
		};
		
		ActionListener loadList = (ActionEvent e)->{
			if(chosenFile!=null) {
				
				
				MouseAdapter mouseListener = new MouseAdapter() {
			      public void mouseClicked(MouseEvent mouseEvent) {
			        @SuppressWarnings("unchecked")
					JList<Query> theList = (JList<Query>) mouseEvent.getSource();
			        if (mouseEvent.getClickCount() == 2) {
			          int index = theList.locationToIndex(mouseEvent.getPoint());
			          if (index >= 0) {
			            Query q = theList.getModel().getElementAt(index);
			            QueryManager.handleQuery(q,target,frame);
			            ((DefaultListModel<Query>) theList.getModel()).removeElementAt(index); 
			          }
			        }
			      }

			
			    };
			
				
				scrollPane.setViewportView(null);
				Scanner sc = null;
				
				try {
					sc = new Scanner(chosenFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, e1.getMessage());
					return;
				}
				
				qList = new ArrayList<>();
				
				while(sc.hasNextLine()){
		            String line = sc.nextLine();
		            if(!line.equals("")) {
		            	Query q = new Query(info,line);
		            	qList.add(q);
		            }
		            
		        }
				
				var model = new DefaultListModel<Query>();
				for(var q : qList) {
					model.addElement( q);
				}
				
				items = new JList<Query>();
				items.setModel(model);
				items.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
				items.setForeground(new Color(30, 144, 255));
				items.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				items.addMouseListener(mouseListener);
				scrollPane.setViewportView(items);
				
				
				sc.close();
			}
			
		};
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JButton fileButton = new JButton("File");
		fileButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		fileButton.setForeground(new Color(30, 144, 255));
		fileButton.setBounds(10, 18, 90, 29);
		fileButton.addActionListener(chooseFile);
		frame.getContentPane().add(fileButton);
		
		fileTextField = new JTextField();
		fileTextField.setEditable(false);
		fileTextField.setBounds(104, 14, 318, 34);
		frame.getContentPane().add(fileTextField);
		fileTextField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 412, 331);
		frame.getContentPane().add(scrollPane);
		
		JButton closeButton = new JButton("cancel");
		closeButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		closeButton.setForeground(new Color(30, 144, 255));
		closeButton.setBounds(6, 407, 117, 29);
		frame.getContentPane().add(closeButton);
		
		closeButton.addActionListener((ActionEvent e)->{
			
			frame.dispose();
		});
		
		loadButton = new JButton("load queries");
		loadButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		loadButton.setForeground(new Color(30, 144, 255));
		loadButton.setBounds(309, 407, 117, 29);
		loadButton.addActionListener(loadList);
		frame.getContentPane().add(loadButton);
		frame.setBounds(100, 100, 432, 467);
		frame.setDefaultCloseOperation(0);
	}
	
	
}
