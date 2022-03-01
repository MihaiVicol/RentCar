package proiect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class CreareCont implements ActionListener {

	private JPanel contentPane;
	JFrame frame=new JFrame();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JPasswordField passwordField;
	public CreareCont() {
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
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Acasa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Acasa();
			}
		});
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Autentificare");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Autentificare();
				
			}
		});
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Despre noi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new DespreNoi(-1);
			}
		});
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Contact");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Contact(-1);
			}
		});
		btnNewButton_3.setMinimumSize(new Dimension(59, 21));
		btnNewButton_3.setMaximumSize(new Dimension(59, 21));
		btnNewButton_3.setBounds(891, 5, 126, 21);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Introduceti datele in casutele de mai jos:");
		lblNewLabel.setBounds(79, 78, 257, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume:");
		lblNewLabel_1.setBounds(123, 133, 72, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setBounds(123, 163, 72, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail:");
		lblNewLabel_3.setBounds(123, 193, 72, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefon:");
		lblNewLabel_4.setBounds(123, 223, 72, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Username:");
		lblNewLabel_5.setBounds(123, 253, 72, 13);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Parola:");
		lblNewLabel_6.setBounds(123, 283, 72, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("CNP:");
		lblNewLabel_7.setBounds(123, 313, 72, 13);
		panel.add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setBounds(200, 130, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 160, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 190, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(200, 220, 96, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(200, 250, 96, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(200, 310, 96, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Creeaza Cont");
		btnNewButton_4.setBounds(141, 368, 120, 21);
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 280, 96, 19);
		panel.add(passwordField);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
  		try {        			
  			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
  			BigInteger b=new BigInteger(textField_6.getText());
  			String q="SELECT CNP FROM utilizatori WHERE CNP='"+b+"'";
  			Statement t=con.createStatement();
			ResultSet r = t.executeQuery(q);
  			if(r.next()==false)
  			{
  			String query= "INSERT INTO utilizatori (Nume, Adresa, Telefon, Email, Username, Parola, CNP, IsAdmin) VALUES (?,?,?,?,?,?,?,0)";                                                                                                 
  			PreparedStatement st=con.prepareStatement(query);
  			st.setString(1, textField.getText());
  			st.setString(2, textField_1.getText());
  			st.setString(3, textField_3.getText());
  			st.setString(4, textField_2.getText());
  			st.setString(5, textField_4.getText());
  			st.setString(6, String.valueOf(passwordField.getPassword()));
  			st.setLong(7, Long.parseLong(textField_6.getText()));
  			int update = st.executeUpdate();
  			if(update>0)
  			{
  				JOptionPane.showMessageDialog(new JFrame(),
  					    "Contul a fost creat cu succes!");
  				frame.dispose();
  				new Autentificare();
  			}
  			st.close();
  			}
  			else
  				JOptionPane.showMessageDialog(new JFrame(), "CNP existent!", "ERROR", JOptionPane.ERROR_MESSAGE);
  		} catch(Exception ed) {
  			JOptionPane.showMessageDialog(new JFrame(), ed, "ERROR", JOptionPane.ERROR_MESSAGE);
  		} finally {
  			try {
  				if(con!=null)
  					con.close();
  			} catch(SQLException ed) {
  				JOptionPane.showMessageDialog(new JFrame(), ed, "ERROR", JOptionPane.ERROR_MESSAGE);
  			}
  		}
	}
}
