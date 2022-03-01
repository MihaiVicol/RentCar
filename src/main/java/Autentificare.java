package proiect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;

public class Autentificare {
	JFrame frame=new JFrame();
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private String start,end;
	public Autentificare() {
		genereazaFereastra();
	}
	public Autentificare(String st, String nd)
	{
		System.out.println(st);
		this.start=st;
		this.end=nd;
		genereazaFereastra();
	}
	public void genereazaFereastra()
	{
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
		panel.setFont(new Font("Tahoma", Font.PLAIN, 10));
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(755, 239, 250, 19);
		panel.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(755, 195, 250, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Conecteaza-te");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con = null;
        		try {        			
        			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
        			String query= "SELECT ID, Username, Parola, IsAdmin FROM utilizatori";
        			Statement st=con.createStatement();
        			ResultSet rs = st.executeQuery(query);
        			String utilizator = textField.getText();
        			String pass = String.valueOf(passwordField.getPassword());
        			boolean ok=false;
        			int ID=-1;
        			int ok1=0;
        			while(rs.next())
        			{
        				String user = rs.getString("Username");
        				String par = rs.getString("Parola");
        				if(user.equals(utilizator) && par.equals(pass))
        				{
        					ok=true;
        					ID=rs.getInt("ID");
        					ok1=rs.getInt("IsAdmin");
        				}
        			}
        			if(ok)
        			{ 
        				if(ok1==1)
        				{
            				frame.dispose();
            				new Administrator();
        				}
        				else if(start!=null&&end!=null)
        				{
        					frame.dispose();
        					new MasiniDisponibile(ID,start,end);
        				}
        				else
        				{
            				frame.dispose();
            				new Acasa(ID);
        				}
        			}
        			else 
        				JOptionPane.showMessageDialog(new JFrame(), "Utilizator sau parola incorecte!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        				
        		} catch(Exception eo) {
        			JOptionPane.showMessageDialog(new JFrame(), eo, "ERROR", JOptionPane.ERROR_MESSAGE);
        		} finally {
        			try {
        				if(con!=null)
        					con.close();
        			} catch(SQLException eo) {
        				JOptionPane.showMessageDialog(new JFrame(), eo, "ERROR", JOptionPane.ERROR_MESSAGE);
        			}
        		}
			}
		});
		btnNewButton_4.setBackground(Color.GREEN);
		btnNewButton_4.setBounds(755, 275, 250, 21);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Creeaza un cont nou");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CreareCont();
			}
		});
		btnNewButton_5.setBackground(Color.MAGENTA);
		btnNewButton_5.setBounds(822, 317, 183, 21);
		panel.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("Nume utilizator");
		lblNewLabel.setBounds(658, 191, 88, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Parola");
		lblNewLabel_1.setBounds(658, 238, 75, 21);
		panel.add(lblNewLabel_1);
		
		JTextPane txtpnKoljoKlkmkJjn = new JTextPane();
		txtpnKoljoKlkmkJjn.setForeground(Color.BLACK);
		txtpnKoljoKlkmkJjn.setBackground(new Color(102, 204, 255));
		txtpnKoljoKlkmkJjn.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		txtpnKoljoKlkmkJjn.setEditable(false);
		txtpnKoljoKlkmkJjn.setText("Bine ati venit!\r\n\r\nRentCar te conecteaz\u0103 cu cele mai importante m\u0103rci din domeniul \u00EEnchirierilor auto.");
		txtpnKoljoKlkmkJjn.setBounds(335, 195, 275, 142);
		panel.add(txtpnKoljoKlkmkJjn);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
