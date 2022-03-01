package proiect;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acasa {

	private JPanel contentPane;
	private JTextField txtnchirieriAuto;
	private JDateChooser st = new JDateChooser();
	JDateChooser nd = new JDateChooser();
	int IDC=-1;
	JFrame frame=new JFrame();
	/**
	 * Create the frame.
	 */
	public Acasa() {
		IDC=-1;
		java.util.Date date=new java.util.Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date=c.getTime();
		st.setDate(date);
		c.add(Calendar.DATE, 1);
		date=c.getTime();
		nd.setDate(date);
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
				if(IDC==-1)
					new Acasa();
				else
					new Acasa(IDC);
				
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
		
		txtnchirieriAuto = new JTextField();
		txtnchirieriAuto.setEnabled(false);
		txtnchirieriAuto.setEditable(false);
		txtnchirieriAuto.setDisabledTextColor(new Color(204, 153, 153));
		txtnchirieriAuto.setBackground(new Color(102, 51, 255));
		txtnchirieriAuto.setSelectedTextColor(new Color(255, 255, 255));
		txtnchirieriAuto.setFont(new Font("Sitka Text", Font.PLAIN, 50));
		txtnchirieriAuto.setText("        \u00CEnchirieri auto - C\u0103uta\u021Bi, compara\u021Bi \u0219i \u00EEnchiria\u021Bi");
		txtnchirieriAuto.setBounds(66, 70, 1356, 103);
		panel.add(txtnchirieriAuto);
		txtnchirieriAuto.setColumns(10);
		 st.setSize(126, 36);
		 st.setLocation(417, 299);
		 nd.setSize(126, 36);
		 nd.setLocation(723, 299);
		 panel.add(st);
		 panel.add(nd);
		 
		 JLabel lblNewLabel = new JLabel("Alegeti perioada->");
		 lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		 lblNewLabel.setBounds(125, 299, 126, 36);
		 panel.add(lblNewLabel);
		 
		 JLabel lblNewLabel_1 = new JLabel("De la:");
		 lblNewLabel_1.setBounds(378, 299, 100, 36);
		 panel.add(lblNewLabel_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("Pana la:");
		 lblNewLabel_2.setBounds(675, 299, 100, 36);
		 panel.add(lblNewLabel_2);
		 
		 JButton btnNewButton_4 = new JButton("Cauta");
		 btnNewButton_4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        String datas = format.format(st.getDate() );
		        String datad = format.format(nd.getDate() );
		        LocalDate Inceput = LocalDate.parse(datas);
				LocalDate Incheiere = LocalDate.parse(datad);
				java.util.Date dates=new java.util.Date();
				long Zile = ChronoUnit.DAYS.between(Inceput, Incheiere);
				if(Zile < 1 || (dates.after(st.getDate())))
				{
					JOptionPane.showMessageDialog(new JFrame(), "Perioada invalida!", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				else{
					frame.dispose();
			        new MasiniDisponibile(IDC,datas,datad);
				}
		 	}
		 });
		 btnNewButton_4.setBounds(1003, 308, 85, 21);
		 panel.add(btnNewButton_4);
		 
		
		 
		 JTextPane txtpnAAA = new JTextPane();
		 txtpnAAA.setEditable(false);
		 txtpnAAA.setBackground(Color.LIGHT_GRAY);
		 txtpnAAA.setText("RentCar iti pune la dispozitie solutia optima pentru o flota flexibila daca esti in cautarea unui autovehicul comercial.\r\nIntrebari frecvente:\r\n              *De ce am nevoie pentru a inchiria o masinia?\r\n                      Pentru a inchiria masina aveti nevoie de:\r\n                           -card de credit\r\n                           -permis\r\n                          -carte de identitate\r\n\r\n              *Care este varsta minima pentru a inchiria o masina?\r\n                     Varsta minima este 21 de ani!\r\n            \r\n              *Cum gasesc cea mai ieftina oferta?\r\n                     Pentru a compara pre\u0163urile \u0219i pentru a v\u0103 gasi ma\u015Fina ideal\u0103 la un pre\u0163 imbatabil, c\u0103utati prin intermediul platformei de c\u0103utare.\r\n\r\n             *Ce anume sa iau in calcul la alegere unei masini?\r\n                     Spa\u021Biul: v\u0103 ve\u021Bi bucura mai mult de ma\u0219ina \u00EEnchiriat\u0103 dac\u0103 alege\u021Bi una cu spa\u021Biu suficient pentru pasageri \u0219i bagaje.\r\n                     Politica de combustibil: Nu pl\u0103nui\u021Bi s\u0103 conduce\u021Bi mult? Politica Aceea\u0219i cantitate v\u0103 poate ajuta s\u0103 economisi\u021Bi bani.\r\n\r\n             *Cat costa inchirierea unei masini in Romania?\r\n                     Pre\u021Bul mediu zilnic pe zi este diferit \u00EEn func\u021Bie de luna anului. \u00CEn medie, o ma\u0219in\u0103 de \u00EEnchiriat \u00EEn Romania cost\u0103 25 Euro pe zi.");
		 txtpnAAA.setBounds(26, 387, 1474, 360);
		 panel.add(txtpnAAA);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
	public Acasa(int ID)
	{
		java.util.Date date=new java.util.Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date=c.getTime();
		st.setDate(date);
		c.add(Calendar.DATE, 1);
		date=c.getTime();
		nd.setDate(date);
		IDC=ID;
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
				if(IDC==-1)
					new Acasa();
				else
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
		
		txtnchirieriAuto = new JTextField();
		txtnchirieriAuto.setEnabled(false);
		txtnchirieriAuto.setEditable(false);
		txtnchirieriAuto.setDisabledTextColor(new Color(204, 153, 153));
		txtnchirieriAuto.setBackground(new Color(102, 51, 255));
		txtnchirieriAuto.setSelectedTextColor(new Color(255, 255, 255));
		txtnchirieriAuto.setFont(new Font("Sitka Text", Font.PLAIN, 50));
		txtnchirieriAuto.setText("        \u00CEnchirieri auto - C\u0103uta\u021Bi, compara\u021Bi \u0219i \u00EEnchiria\u021Bi");
		txtnchirieriAuto.setBounds(66, 70, 1356, 103);
		panel.add(txtnchirieriAuto);
		txtnchirieriAuto.setColumns(10);
		 st.setSize(126, 36);
		 st.setLocation(417, 299);
		 nd.setSize(126, 36);
		 nd.setLocation(723, 299);
		 panel.add(st);
		 panel.add(nd);
		 
		 JLabel lblNewLabel = new JLabel("Alegeti perioada->");
		 lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		 lblNewLabel.setBounds(125, 299, 126, 36);
		 panel.add(lblNewLabel);
		 
		 JLabel lblNewLabel_1 = new JLabel("De la:");
		 lblNewLabel_1.setBounds(378, 299, 100, 36);
		 panel.add(lblNewLabel_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("Pana la:");
		 lblNewLabel_2.setBounds(675, 299, 100, 36);
		 panel.add(lblNewLabel_2);
		 
		 JButton btnNewButton_4 = new JButton("Cauta");
		 btnNewButton_4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        String datas = format.format(st.getDate() );
		        String datad = format.format(nd.getDate() );
		        LocalDate Inceput = LocalDate.parse(datas);
				LocalDate Incheiere = LocalDate.parse(datad);
				java.util.Date dates=new java.util.Date();
				long Zile = ChronoUnit.DAYS.between(Inceput, Incheiere);
				if(Zile < 1||dates.after(st.getDate()))
				{
					JOptionPane.showMessageDialog(new JFrame(), "Perioada invalida!", "ERROR", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
					new Acasa(IDC);
				}
				else {
					frame.dispose();
			        new MasiniDisponibile(IDC,datas,datad);
				}
		 	}
		 });
		 btnNewButton_4.setBounds(1003, 308, 85, 21);
		 panel.add(btnNewButton_4);
		 
		 JTextPane txtpnAAA = new JTextPane();
		 txtpnAAA.setEditable(false);
		 txtpnAAA.setBackground(Color.LIGHT_GRAY);
		 txtpnAAA.setText("RentCar iti pune la dispozitie solutia optima pentru o flota flexibila daca esti in cautarea unui autovehicul comercial.\r\nIntrebari frecvente:\r\n              *De ce am nevoie pentru a inchiria o masinia?\r\n                      Pentru a inchiria masina aveti nevoie de:\r\n                           -card de credit\r\n                           -permis\r\n                          -carte de identitate\r\n\r\n              *Care este varsta minima pentru a inchiria o masina?\r\n                     Varsta minima este 21 de ani!\r\n            \r\n              *Cum gasesc cea mai ieftina oferta?\r\n                     Pentru a compara pre\u0163urile \u0219i pentru a v\u0103 gasi ma\u015Fina ideal\u0103 la un pre\u0163 imbatabil, c\u0103utati prin intermediul platformei de c\u0103utare.\r\n\r\n             *Ce anume sa iau in calcul la alegere unei masini?\r\n                     Spa\u021Biul: v\u0103 ve\u021Bi bucura mai mult de ma\u0219ina \u00EEnchiriat\u0103 dac\u0103 alege\u021Bi una cu spa\u021Biu suficient pentru pasageri \u0219i bagaje.\r\n                     Politica de combustibil: Nu pl\u0103nui\u021Bi s\u0103 conduce\u021Bi mult? Politica Aceea\u0219i cantitate v\u0103 poate ajuta s\u0103 economisi\u021Bi bani.\r\n\r\n             *Cat costa inchirierea unei masini in Romania?\r\n                     Pre\u021Bul mediu zilnic pe zi este diferit \u00EEn func\u021Bie de luna anului. \u00CEn medie, o ma\u0219in\u0103 de \u00EEnchiriat \u00EEn Romania cost\u0103 25 Euro pe zi.");
		 txtpnAAA.setBounds(26, 387, 1474, 360);
		 panel.add(txtpnAAA);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
