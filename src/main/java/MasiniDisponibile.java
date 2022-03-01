package proiect;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MasiniDisponibile {

	private JPanel contentPane;
	private JButton btnNewButton_1 = new JButton("Autentificare");
	JFrame frame=new JFrame();
	private String start="";
	private String end="";
	private int IDC;
	public MasiniDisponibile(int IDc, String sta, String nd) {
		start=sta;
    	end=nd;
    	IDC=IDc;
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
		
		JButton btnNewButton = new JButton("Acasa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if(IDC!=-1)
				new Acasa(IDC);
				else
					new Acasa();
			}
		});
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		int i=108;
		if(IDc!=-1)
			btnNewButton_1.setText("Contul meu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if(IDC==-1)
				new Autentificare();
				else
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
		Connection con = null;
		try {  
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			String query= "SELECT ID, Marca, Model, Motor, Combustibil, AnFabricatie, Culoare, PretZi, Imagini "
					+ "FROM autoturisme WHERE ID NOT IN (SELECT ID_Autoturism FROM programari "
					+ "WHERE ('"+start+"' BETWEEN DataPreluare AND DataReturnare) OR ('"+end+"' BETWEEN DataPreluare AND DataReturnare) OR ('"+start+"'<=DataPreluare AND '"+end+"'>=DataReturnare))";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
            	JPanel panel_1 = new JPanel();
        		panel_1.setBounds(47, i, 1381, 184);
        		panel.add(panel_1);
        		panel_1.setLayout(null);
        		int ID = rs.getInt("ID");
            	byte[] imag= rs.getBytes("Imagini");
    			Image img = Toolkit.getDefaultToolkit().createImage(imag);
    			Image img2 = img.getScaledInstance(250, 160, Image.SCALE_DEFAULT);
    			ImageIcon icon = new ImageIcon(img2);
        		
        		JLabel lblNewLabel_2 = new JLabel();
        		lblNewLabel_2.setBounds(10, 10, 250, 160);
        		lblNewLabel_2.setIcon(icon);
        		panel_1.add(lblNewLabel_2);
        		
        		JLabel lblNewLabel_3 = new JLabel(rs.getString("Marca") + " " + rs.getString("Model"));
        		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        		lblNewLabel_3.setBounds(297, 10, 170, 47);
        		panel_1.add(lblNewLabel_3);
        		
        		JLabel lblNewLabel_4 = new JLabel(rs.getString("Combustibil"));
        		lblNewLabel_4.setBounds(530, 29, 104, 13);
        		panel_1.add(lblNewLabel_4);
        		LocalDate Inceput = LocalDate.parse(start);
				LocalDate Incheiere = LocalDate.parse(end);
				long Zile = ChronoUnit.DAYS.between(Inceput, Incheiere);
				String pr=new DecimalFormat("0.00").format(Zile*Float.parseFloat(rs.getString("PretZi")));
        		JLabel lblNewLabel_5 = new JLabel(pr + "Lei");
        		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        		lblNewLabel_5.setBounds(1260, 29, 114, 61);
        		panel_1.add(lblNewLabel_5);
        		
        		JLabel lblNewLabel_6 = new JLabel(rs.getString("AnFabricatie"));
        		lblNewLabel_6.setBounds(710, 29, 55, 13);
        		panel_1.add(lblNewLabel_6);
        		
        		JLabel lblNewLabel_7 = new JLabel(rs.getString("Culoare"));
        		lblNewLabel_7.setBounds(877, 29, 55, 13);
        		panel_1.add(lblNewLabel_7);
        		
        		JLabel lblNewLabel_8 = new JLabel(rs.getString("Motor") + " cmc");
        		lblNewLabel_8.setBounds(1045, 29, 55, 13);
        		panel_1.add(lblNewLabel_8);
        		
        		JButton btnNewButton_4 = new JButton("Rezerva");
        		btnNewButton_4.setBackground(Color.GREEN);
        		btnNewButton_4.setBounds(1258, 133, 85, 21);
        		panel_1.add(btnNewButton_4);
        		
        		if(IDC!=-1) {
        			btnNewButton_4.addActionListener(new DataActionListener(IDC, ID, start, end, frame)); 
    	        }
    	        else {
    	        	btnNewButton_4.addActionListener(new DataActionListener(IDC, start, end, frame));    
    	        }
        		
        		i=i+190;
            }
            
            
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
		panel.setSize(new Dimension(1620, i));
		panel.setPreferredSize(new Dimension(1500, i));
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(47, 108, 1381, 184);
		//panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Imagine");
		lblNewLabel_2.setBounds(10, 10, 250, 160);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Marca Model");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(297, 10, 170, 47);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Combustibil");
		lblNewLabel_4.setBounds(530, 29, 104, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pret");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(1269, 29, 74, 61);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("An");
		lblNewLabel_6.setBounds(710, 29, 45, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Culoare");
		lblNewLabel_7.setBounds(877, 29, 45, 13);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Motor");
		lblNewLabel_8.setBounds(1045, 29, 45, 13);
		panel_1.add(lblNewLabel_8);
		
		JButton btnNewButton_4 = new JButton("Rezerva");
		btnNewButton_4.setBackground(Color.GREEN);
		btnNewButton_4.setBounds(1258, 133, 85, 21);
		panel_1.add(btnNewButton_4);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
