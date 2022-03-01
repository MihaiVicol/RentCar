package proiect;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SchimbaParola {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFrame frame=new JFrame();
	private int ID=0;
	public SchimbaParola(int IDc) {
		ID=IDc;
		frame.setPreferredSize(new Dimension(400, 220));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 400, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Parola noua");
		lblNewLabel.setBounds(50, 51, 85, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Confirma parola");
		lblNewLabel_1.setBounds(50, 84, 120, 13);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 81, 99, 19);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(198, 48, 99, 19);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Trimite");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword()))==true)
				{
				Connection con = null;
 	     		try {        			
 	     			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
 	     			String query= "UPDATE utilizatori SET parola=? WHERE ID='"+ID+"'";                                                                                                 
 	     			PreparedStatement st=con.prepareStatement(query);
 	     			st.setString(1, String.valueOf(passwordField.getPassword()));
 	     			int update = st.executeUpdate();
 	     			if(update>0)
 	     			{
 	     				JOptionPane.showMessageDialog(new JFrame(),
 	     					    "Parola a fost schimbata!");
 	     				frame.dispose();
 	     			}
 	     			st.close();
 	     		} catch(Exception ep) {
 	     			JOptionPane.showMessageDialog(new JFrame(), ep, "ERROR", JOptionPane.ERROR_MESSAGE);
 	     		} finally {
 	     			try {
 	     				if(con!=null)
 	     					con.close();
 	     			} catch(SQLException ep) {
 	     				JOptionPane.showMessageDialog(new JFrame(), ep, "ERROR", JOptionPane.ERROR_MESSAGE);
 	     			}
 	     		}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Parolele nu corespund", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(134, 129, 85, 21);
		contentPane.add(btnNewButton);
		
		frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
	}
}
