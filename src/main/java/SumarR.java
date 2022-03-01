package proiect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class SumarR{
	private JLabel lblNewLabel_7 = new JLabel("Conducator: ");
	private JLabel lblNewLabel_1 = new JLabel("Autoturism:");
	private JLabel lblNewLabel_3 = new JLabel("De la:");
	private JLabel lblNewLabel_4 = new JLabel("Pana la:");
	private JLabel lblNewLabel_5 = new JLabel("Pret total:");
	private JLabel lblNewLabel_6 = new JLabel("Combustibil:");
	float pret=0;
	int IDC,IDA;
	String start,end;
	private JPanel contentPane;
	private JFrame frame=new JFrame();
	private void Placeholder() 
	{
		Connection con = null;
		try {  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			String query= "SELECT Nume FROM utilizatori e, autoturisme WHERE e.ID='"+IDC+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			lblNewLabel_7.setText("Conducator: " + rs.getString("Nume"));
			query= "SELECT Marca, Model, Combustibil, PretZi FROM autoturisme WHERE ID='"+IDA+"'";
			Statement stA=con.createStatement();
			ResultSet rsA = stA.executeQuery(query);
			rsA.next();
			lblNewLabel_1.setText("Autoturism: " + rsA.getString("Marca")+rsA.getString("Model"));
			lblNewLabel_6.setText("Combustibil: " + rsA.getString("Combustibil"));
			pret = rsA.getFloat("PretZi");	
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
		lblNewLabel_3.setText("De la: " + start);
		lblNewLabel_4.setText("Pana la: " + end);
		LocalDate Inceput = LocalDate.parse(start);
		LocalDate Incheiere = LocalDate.parse(end);
		long Zile = ChronoUnit.DAYS.between(Inceput, Incheiere);
		pret=pret*Zile;
		lblNewLabel_5.setText("Pret total: " + Float.toString(pret) + "Lei");
	}
	public SumarR(int idC, int idA, String sta, String nd) {
		IDC=idC;
		IDA=idA;
		start=sta;
		end=nd;
		Placeholder();
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
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(102, 204, 255));
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Acasa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Acasa(IDC);
			}
		});
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Contul meu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ContulMeu(IDC);
				
			}
		});
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Despre noi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new DespreNoi(IDC);
			}
		});
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Contact");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Contact(IDC);
			}
		});
		btnNewButton_3.setMinimumSize(new Dimension(59, 21));
		btnNewButton_3.setMaximumSize(new Dimension(59, 21));
		btnNewButton_3.setBounds(891, 5, 126, 21);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Sumar rezervare");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(682, 90, 213, 78);
		panel.add(lblNewLabel);
		
		lblNewLabel_1.setBounds(484, 200, 206, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Perioada:");
		lblNewLabel_2.setBounds(484, 237, 105, 13);
		panel.add(lblNewLabel_2);

		lblNewLabel_3.setBounds(620, 237, 126, 13);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4.setBounds(769, 237, 112, 13);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5.setBounds(484, 321, 155, 13);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6.setBounds(484, 281, 155, 13);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_4 = new JButton("Finalizare rezervare");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
 	     		try {        			
 	     			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
 	     			String query= "INSERT INTO programari (ID_Client, ID_Autoturism, DataPreluare, DataReturnare, Pret, CheckOut) VALUES (?,?,?,?,?,?)";                                                                                                 
 	     			PreparedStatement st=con.prepareStatement(query);
 	     			st.setInt(1, IDC);
 	     			st.setInt(2, IDA);
 	     			st.setString(3, start);
 	     			st.setString(4, end);
 	     			st.setFloat(5, pret);
 	     			st.setFloat(6, 0);
 	     			int update = st.executeUpdate();
 	     			if(update>0)
 	     			{
 	     				JOptionPane.showMessageDialog(new JFrame(),
 	     					    "Rezervarea a fost trimisa!");
 	     				frame.dispose();
 	     				new Acasa(IDC);
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
		});
		btnNewButton_4.setBackground(Color.GREEN);
		btnNewButton_4.setBounds(856, 392, 232, 21);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Inapoi");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MasiniDisponibile(IDC, start,end);
			}
		});
		btnNewButton_5.setBackground(Color.RED);
		btnNewButton_5.setBounds(882, 441, 85, 21);
		panel.add(btnNewButton_5);
		
		lblNewLabel_7.setBounds(484, 164, 206, 13);
		panel.add(lblNewLabel_7);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
