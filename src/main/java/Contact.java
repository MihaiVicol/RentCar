package proiect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextPane;

public class Contact {

	private JPanel contentPane;
	JFrame frame=new JFrame();
	private int ID;
	public Contact(int idC) {
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
		panel.setPreferredSize(new Dimension(1500, 720));
		panel.setSize(new Dimension(1620, 880));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Acasa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Acasa(ID);
			}
		});
		btnNewButton.setBounds(484, 5, 126, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		if(ID!=-1)
			btnNewButton_1.setText("Contul meu");
		else
			btnNewButton_1.setText("Autentificare");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if(ID==-1)
				new Autentificare();
				else
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
		
		JTextPane txtpnSunatiLa = new JTextPane();
		txtpnSunatiLa.setFont(new Font("Tahoma", Font.BOLD, 28));
		txtpnSunatiLa.setText("Sunati la 0701234569\r\nAdresa e-mail: rentcarRC@yahoo.ro\r\nAdresa: strada RentCar, Bucuresti\r\nLuni-Vineri:  8:00-19:00");
		txtpnSunatiLa.setBounds(495, 275, 509, 152);
		panel.add(txtpnSunatiLa);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.getContentPane().add(contentPane);
		 frame.pack();
		 frame.setVisible(true);
	}
}
