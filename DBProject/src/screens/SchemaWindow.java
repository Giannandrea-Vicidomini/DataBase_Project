package screens;


import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SchemaWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public SchemaWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 444, 630);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(LoginWindow.class.getResource("/resources/Logic-schema.png"));
		Image image1 = imageIcon.getImage(); // transform it 
		Image newimg = image1.getScaledInstance(444, 600,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		imageIcon = new ImageIcon(newimg); 
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(2, 1, 448, 605);
		frame.getContentPane().add(lblNewLabel);
		
		frame.setVisible(true);
	}
}
