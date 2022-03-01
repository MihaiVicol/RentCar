package proiect;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
public class ContulMeu{

	private JPanel contentPane;
	private JFrame frame= new JFrame();
	private int ID;
	public ContulMeu(int idC) {
		ID=idC;
		frame.setPreferredSize(new Dimension(1500, 720));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 1500, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 204, 255));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		int i=266;
		Connection con = null;
		try {  
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			String query= "SELECT ID, ID_Autoturism, DataPreluare, DataReturnare FROM programari WHERE ID_Client='"+ID+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
            	String querya= "SELECT Model, Marca FROM autoturisme WHERE ID='"+rs.getInt("ID_Autoturism")+"'";
    			Statement sta=con.createStatement();
    			ResultSet rsa = sta.executeQuery(querya);
    			rsa.next();
    			JPanel panel_1 = new JPanel();
    			panel_1.setBounds(514, i, 500, 117);
    			panel.add(panel_1);
    			panel_1.setLayout(null);
    			
    			JButton btnNewButton_5 = new JButton("Mai multe detalii...");
    			btnNewButton_5.setBounds(330, 86, 140, 21);
    			panel_1.add(btnNewButton_5);
    			
    			JLabel lblNewLabel_2 = new JLabel("Data");
    			lblNewLabel_2.setBounds(34, 35, 45, 13);
    			panel_1.add(lblNewLabel_2);
    			
    			JLabel lblNewLabel_3 = new JLabel("Masina");
    			lblNewLabel_3.setBounds(189, 35, 45, 13);
    			panel_1.add(lblNewLabel_3);
    			
    			JLabel lblNewLabel_4 = new JLabel("De la: " + rs.getString("DataPreluare"));
    			lblNewLabel_4.setBounds(34, 58, 145, 13);
    			panel_1.add(lblNewLabel_4);
    			
    			JLabel lblNewLabel_5 = new JLabel("Pana la: " + rs.getString("DataReturnare"));
    			lblNewLabel_5.setBounds(34, 81, 145, 13);
    			panel_1.add(lblNewLabel_5);
    			
    			JLabel lblNewLabel_6 = new JLabel(rsa.getString("Marca")+ " " + rsa.getString("Model"));
    			lblNewLabel_6.setBounds(189, 68, 100, 13);
    			panel_1.add(lblNewLabel_6);
    			btnNewButton_5.addActionListener(new DataActionListener(rs.getInt("ID"),true));
    			i=i+120;
            }
            
            String querya= "SELECT Nume FROM utilizatori WHERE ID='"+ID+"'";
			Statement sta=con.createStatement();
			ResultSet rsa = sta.executeQuery(querya);
			rsa.next();
            JLabel lblNewLabel = new JLabel("Bine ai venit, "+ rsa.getString("Nume"));
    		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
    		lblNewLabel.setBounds(109, 109, 300, 28);
    		panel.add(lblNewLabel);
            
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
		
		panel.setPreferredSize(new Dimension(1500, i));
		panel.setSize(new Dimension(1620, i));
		
		JButton btnNewButton = new JButton("Acasa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Acasa(ID);
			}
		});
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Contul meu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ContulMeu(ID);
				
			}
		});
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Despre noi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new DespreNoi(ID);
			}
		});
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Contact");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Contact(ID);
			}
		});
		btnNewButton_3.setMinimumSize(new Dimension(59, 21));
		btnNewButton_3.setMaximumSize(new Dimension(59, 21));
		btnNewButton_3.setBounds(891, 5, 126, 21);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Istoric rezervari");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setBounds(588, 152, 415, 57);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Schimba parola");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SchimbaParola(ID);
			}
		});
		
		btnNewButton_4.setBackground(Color.YELLOW);
		btnNewButton_4.setBounds(1190, 116, 167, 21);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_19 = new JButton("Deconectare");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Acasa();
			}
		});
		
		btnNewButton_19.setBackground(Color.RED);
		btnNewButton_19.setBounds(1190, 170, 167, 21);
		panel.add(btnNewButton_19);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(514, 266, 524, 117);
		//panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_5 = new JButton("Mai multe detalii...");
		btnNewButton_5.setBounds(358, 86, 156, 21);
		panel_1.add(btnNewButton_5);
		
		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(34, 35, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Masina");
		lblNewLabel_3.setBounds(189, 35, 45, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(34, 58, 145, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(34, 81, 138, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(189, 68, 171, 13);
		panel_1.add(lblNewLabel_6);
		
		
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
