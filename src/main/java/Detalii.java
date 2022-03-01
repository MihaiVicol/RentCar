package proiect;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Detalii {

	private JPanel contentPane;
	JFrame frame=new JFrame();
	private int ID;
	public Detalii(int idp) {
		ID=idp;
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 600, 400);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		Connection con = null;
		try {  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			String query= "SELECT ID_Autoturism, DataPreluare, DataReturnare, Pret FROM programari WHERE ID='"+ID+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			String querya= "SELECT Marca, Model, Motor, Combustibil, AnFabricatie, Culoare, PretZi, Imagini FROM autoturisme ";
			Statement sta=con.createStatement();
			ResultSet rsa= sta.executeQuery(querya);
			rsa.next();
			
			JLabel lblNewLabel = new JLabel("Data: De la " + rs.getString("DataPreluare") + " Pana la " + rs.getString("DataReturnare"));
			lblNewLabel.setBounds(10, 10, 275, 13);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Masina: " + rsa.getString("Marca") + " " + rsa.getString("Model"));
			lblNewLabel_1.setBounds(10, 33, 275, 13);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("An fabricatie: " + rsa.getString("AnFabricatie"));
			lblNewLabel_2.setBounds(10, 56, 275, 13);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Combustibil: " + rsa.getString("Combustibil"));
			lblNewLabel_3.setBounds(10, 79, 275, 13);
			contentPane.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Culoare: "+rsa.getString("Culoare"));
			lblNewLabel_4.setBounds(10, 102, 275, 13);
			contentPane.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Capacitate motor: "+rsa.getString("Motor") + "cmc");
			lblNewLabel_5.setBounds(10, 125, 275, 13);
			contentPane.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Pret: "+rs.getString("Pret")+" Lei");
			lblNewLabel_6.setBounds(10, 148, 275, 13);
			contentPane.add(lblNewLabel_6);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e, "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if(con!=null)
					con.close();
			} catch(SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), e, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
	}

}
