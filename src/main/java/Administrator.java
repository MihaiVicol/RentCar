package proiect;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Administrator implements ActionListener {

	private JPanel contentPane;
	JFrame frame = new JFrame();
	JScrollPane scrollPane = new JScrollPane();
	JPanel panel = new JPanel();
	private JButton btnNewButton_5 = new JButton("Trimite");
	private JButton btnNewButton_3 = new JButton("Vizualizare programari");
	private JButton btnNewButton_2 = new JButton("Checkout");
	private JButton btnNewButton_1 = new JButton("Sterge");
	private JButton btnNewButton = new JButton("Adauga");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_11;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private File fin;
	JFileChooser f=new JFileChooser("C:\\Users\\mihai\\Desktop");
	private JTable table;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	public Administrator() {
		frame.setPreferredSize(new Dimension(1500, 720));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 1500, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		
		panel.setBackground(new Color(102, 204, 255));
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		VizualizareProgramari();
		
		
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton_1)
			Sterge();
		else if(e.getSource()==btnNewButton)
			Adauga();
		else if(e.getSource()==btnNewButton_2)
			CheckOut();
		else if(e.getSource()==btnNewButton_3)
			VizualizareProgramari();
		else if(e.getSource()==btnNewButton_5)
			SDB();
	}
	public void Sterge()
	{
		
		panel.removeAll();
		System.gc();
		int i=108;
		Connection con = null;
		try {  
			panel.setBackground(new Color(102, 204, 255));
			btnNewButton.addActionListener(this);
			btnNewButton.setBounds(484, 5, 126, 21);
			panel.add(btnNewButton);
			
			btnNewButton_1.addActionListener(this);
			btnNewButton_1.setBounds(620, 5, 126, 21);
			panel.add(btnNewButton_1);
			
			btnNewButton_2.addActionListener(this);
			btnNewButton_2.setBounds(755, 5, 126, 21);
			panel.add(btnNewButton_2);
			
			btnNewButton_3.addActionListener(this);
			btnNewButton_3.setBounds(891, 5, 207, 21);
			panel.add(btnNewButton_3);
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			String query= "SELECT ID, Marca, Model, Motor, Combustibil, AnFabricatie, Culoare, PretZi, Imagini "
					+ "FROM autoturisme";
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
        		JLabel lblNewLabel_5 = new JLabel(rs.getString("PretZi") + "Lei");
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
        		
        		JButton btnNewButton_4 = new JButton("Sterge");
        		btnNewButton_4.setBackground(Color.GREEN);
        		btnNewButton_4.setBounds(1258, 133, 85, 21);
        		panel_1.add(btnNewButton_4);
        		
        		btnNewButton_4.addActionListener(new DataActionListener(ID, 1,frame)); 
        		
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
		
	}
	
	public void Adauga()
	{
		panel.removeAll();
		System.gc();
		panel.setBackground(new Color(102, 204, 255));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(891, 5, 207, 21);
		panel.add(btnNewButton_3);
		
		
		JLabel lblNewLabel = new JLabel("Marca:");
		lblNewLabel.setBounds(484, 104, 126, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(620, 101, 126, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Model:");
		lblNewLabel_1.setBounds(484, 156, 126, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(620, 153, 126, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Motor (cmc): ");
		lblNewLabel_9.setBounds(484, 208, 126, 13);
		panel.add(lblNewLabel_9);
		
		textField_2 = new JTextField();
		textField_2.setBounds(620, 205, 126, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Combustibil:");
		lblNewLabel_10.setBounds(484, 257, 126, 13);
		panel.add(lblNewLabel_10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(620, 254, 126, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Culoare:");
		lblNewLabel_18.setBounds(484, 309, 126, 13);
		panel.add(lblNewLabel_18);
		
		textField_11 = new JTextField();
		textField_11.setBounds(620, 306, 126, 19);
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("An Fabricatie:");
		lblNewLabel_11.setBounds(484, 361, 126, 13);
		panel.add(lblNewLabel_11);
		
		textField_4 = new JTextField();
		textField_4.setBounds(620, 358, 126, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("PretZi:");
		lblNewLabel_12.setBounds(484, 413, 126, 13);
		panel.add(lblNewLabel_12);
		
		textField_5 = new JTextField();
		textField_5.setBounds(620, 410, 126, 19);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Stare:");
		lblNewLabel_13.setBounds(484, 465, 126, 13);
		panel.add(lblNewLabel_13);
		
		textField_6 = new JTextField();
		textField_6.setBounds(620, 463, 126, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		f.setSize(397, 313);
		f.setLocation(1013, 104);
		panel.add(f);
		f.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  int status = f.showOpenDialog(null);
		    	    if (status == JFileChooser.APPROVE_OPTION)
		    	    	fin=f.getSelectedFile();
		      }});
		
		JLabel lblNewLabel_15 = new JLabel("Fotografie:");
		lblNewLabel_15.setBounds(891, 257, 126, 13);
		panel.add(lblNewLabel_15);
		
		btnNewButton_5.addActionListener(this);
		btnNewButton_5.setBounds(725, 513, 100, 21);
		panel.add(btnNewButton_5);
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
	}
	
	public void SDB()
	{
		Connection con = null;
  		try {        			
  			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
  			String query= "INSERT INTO autoturisme (Marca, Model, Motor, Combustibil, AnFabricatie, Culoare, PretZi, Imagini, Stare) VALUES (?,?,?,?,?,?,?,?,?)";                                                                                                 
  			PreparedStatement st=con.prepareStatement(query);
  			st.setString(1, textField.getText());
  			st.setString(2,textField_1.getText());
  			st.setInt(3, Integer.parseInt(textField_2.getText()));
  			st.setString(4, textField_3.getText());
  			st.setInt(5, Integer.parseInt(textField_4.getText()));
  			st.setString(6, textField_11.getText());
  			st.setFloat(7, Float.parseFloat(textField_5.getText()));
  			st.setBlob(8, new FileInputStream(fin));
  			st.setString(9, textField_6.getText());
  			int update = st.executeUpdate();
  			if(update>0)
  			{
  				JOptionPane.showMessageDialog(new JFrame(),
  					    "Autoturismul a fost adaugat!");
  				frame.dispose();
  				new Administrator();
  			}
  			st.close();
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
	}
	
	
	public void CheckOut()
	{
		panel.removeAll();
		System.gc();
		panel.setBackground(new Color(102, 204, 255));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(891, 5, 207, 21);
		panel.add(btnNewButton_3);
		
		
		
		int i=78;
		Connection con = null;
		try {  
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
			Date d=new Date();
			String query= "SELECT ID, ID_Client, ID_Autoturism, DataReturnare, Pret FROM programari WHERE  CheckOut=0 ";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("sdf");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 String data= df.format(d);
            while(rs.next())
            {
            	String querya= "SELECT Nume, Marca, Model, b.ID, Stare FROM utilizatori a, autoturisme b WHERE a.ID = '"+rs.getInt("ID_Client")+"' && b.ID = '"+rs.getInt("ID_Autoturism")+"' ";
    			Statement sta=con.createStatement();
    			ResultSet rsa = sta.executeQuery(querya);
    			rsa.next();
    			JPanel panel_1 = new JPanel();
    			panel_1.setBounds(10, i, 1479, 167);
    			panel.add(panel_1);
    			panel_1.setLayout(null);
    			
    			JLabel lblNewLabel_14 = new JLabel(rsa.getString("Nume"));
    			lblNewLabel_14.setBounds(66, 31, 99, 13);
    			panel_1.add(lblNewLabel_14);
    			JLabel lblNewLabel_16 = new JLabel(rsa.getString("Marca") + " " + rsa.getString("Model"));
    			lblNewLabel_16.setBounds(66, 94, 99, 13);
    			
    			panel_1.add(lblNewLabel_16);
    			
    			JTextPane textPane = new JTextPane();
    			textPane.setText(rsa.getString("Stare"));
    			textPane.setEnabled(false);
    			textPane.setEditable(false);
    			textPane.setBounds(473, 10, 99, 53);
    			panel_1.add(textPane);
    			
    			JLabel lblNewLabel_17 = new JLabel("Stare Predare");
    			lblNewLabel_17.setBounds(347, 31, 100, 13);
    			panel_1.add(lblNewLabel_17);
    			
    			JTextPane textPane_1 = new JTextPane();
    			textPane_1.setBounds(473, 74, 99, 53);
    			panel_1.add(textPane_1);
    			
    			JLabel lblNewLabel_18 = new JLabel("Stare Returnare");
    			lblNewLabel_18.setBounds(347, 94, 100, 13);
    			panel_1.add(lblNewLabel_18);
    			
    			JLabel lblNewLabel_19 = new JLabel("Pret Combustibil:");
    			lblNewLabel_19.setBounds(664, 31, 100, 13);
    			panel_1.add(lblNewLabel_19);
    			
    			JLabel lblNewLabel_20 = new JLabel("Pret Daune:");
    			lblNewLabel_20.setBounds(664, 94, 100, 13);
    			panel_1.add(lblNewLabel_20);
    			
    			textField_7 = new JTextField();
    			textField_7.setText("0");
    			textField_7.setBounds(785, 28, 96, 19);
    			panel_1.add(textField_7);
    			textField_7.setColumns(10);
    			
    			textField_8 = new JTextField();
    			textField_8.setText("0");
    			textField_8.setBounds(785, 91, 96, 19);
    			panel_1.add(textField_8);
    			textField_8.setColumns(10);
    			
    			JLabel lblNewLabel_21 = new JLabel("Pret Intarzieri");
    			lblNewLabel_21.setBounds(963, 31, 105, 13);
    			panel_1.add(lblNewLabel_21);
    			
    			JLabel lblNewLabel_22 = new JLabel("Pret Total: ");
    			lblNewLabel_22.setBounds(963, 94, 100, 13);
    			panel_1.add(lblNewLabel_22);
    			
    			JButton btnNewButton_7 = new JButton("Add");
    			btnNewButton_7.setBounds(885, 28, 70, 21);
    			panel_1.add(btnNewButton_7);
    			
    			JButton btnNewButton_8 = new JButton("Add");
    			btnNewButton_8.setBounds(885, 91, 70, 21);
    			panel_1.add(btnNewButton_8);
    			
    			textField_9 = new JTextField();
    			textField_9.setText("0");
    			textField_9.setBounds(1068, 28, 96, 19);
    			panel_1.add(textField_9);
    			textField_9.setColumns(10);
    			
    			textField_10 = new JTextField();
    			textField_10.setText(Float.toString(rs.getFloat("Pret")));
    			textField_10.setBounds(1068, 91, 96, 19);
    			panel_1.add(textField_10);
    			textField_10.setColumns(10);
    			
    			
    			LocalDate Inceput = LocalDate.parse(rs.getString("DataReturnare"));
				LocalDate Incheiere = LocalDate.parse(data);
				long Zile = ChronoUnit.DAYS.between(Inceput, Incheiere);
    			if(Zile>1)
    			{
    				float pr=rs.getFloat("Pret");
    				textField_9.setText(Float.toString(pr/2*Zile));
    				textField_10.setText(Float.toString(pr+(pr/2*Zile)));
    			}
    			
    			
    			btnNewButton_7.addActionListener(new DataActionListener(textField_10,textField_7));
    			btnNewButton_8.addActionListener(new DataActionListener(textField_10,textField_8));
    			
    			JLabel lblNewLabel_23 = new JLabel("Data: " + data);
    			lblNewLabel_23.setBounds(1210, 60, 100, 13);
    			panel_1.add(lblNewLabel_23);
    			
    			JButton btnNewButton_6 = new JButton("Finalizeaza Checkout");
    			btnNewButton_6.setBounds(698, 136, 175, 21);
    			panel_1.add(btnNewButton_6);
    			btnNewButton_6.addActionListener(new DataActionListener(rs.getInt("ID"),rsa.getInt("b.ID"),textPane.getText(),textPane_1,Float.parseFloat(textField_7.getText()),Float.parseFloat(textField_8.getText()),Float.parseFloat(textField_9.getText()),Float.parseFloat(textField_10.getText()),data,frame));
    			i=i+170;
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
		
		
		
		
		panel.setPreferredSize(new Dimension(1500, i));
		panel.setSize(new Dimension(1500, i));
	}
	public void VizualizareProgramari()
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.setBackground(new Color(102, 204, 255));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(620, 5, 126, 21);
		panel.add(btnNewButton_1);
		
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(755, 5, 126, 21);
		panel.add(btnNewButton_2);
		
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(891, 5, 207, 21);
		panel.add(btnNewButton_3);
		
		scrollPane_1.setBounds(450, 176, 700, 400);
		panel.add(scrollPane_1);
		table = new JTable(new DefaultTableModel(new Object[]{"ID", "Client", "Autoturism", "DataPreluare", "DataReturnare", "Pret", "Checkout"}, 0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		 Connection con = null;
  		try {        			
  			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
  			String query= "SELECT * FROM programari";                                                                                                 
  			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
  			while(rs.next())
  			{
  				String query2="SELECT Nume FROM utilizatori WHERE ID='"+rs.getInt("ID_Client")+"'";
  				Statement st2=con.createStatement();
  				ResultSet rs2 = st2.executeQuery(query2);
  				rs2.next();
  				String query3="SELECT Marca, Model FROM autoturisme WHERE ID='"+rs.getInt("ID_Autoturism")+"'";
  				Statement st3=con.createStatement();
  				ResultSet rs3 = st3.executeQuery(query3);
  				rs3.next();
  				model.addRow(new Object[]{rs.getInt("ID"), rs2.getString("Nume"), rs3.getString("Marca") + " " + rs3.getString("Model"), rs.getString("DataPreluare"), rs.getString("DataReturnare"), rs.getFloat("Pret"), rs.getBoolean("CheckOut") });
  			}
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
		table.setBounds(100,400,700,200);
		table.setPreferredSize(new Dimension(700,400));
		scrollPane_1.setViewportView(table);
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
	}
}
