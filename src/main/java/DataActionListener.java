package proiect;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataActionListener implements ActionListener{
	private int IDC, IDA, IDP, okS=-1,okCO=-1, okTXT=-1;
	private String start,end,STP,DC;
	private float Daune, PCL, PI, PT;
	private JTextField txt,txta;
	private JTextPane STR;
	private JFrame fr;
	private boolean ok=false;
	/**
	 * Class Constructor if user is logged in
	 * @param idC ID of user who is logged in
	 * @param idA ID of chosen car
	 * @param st Start date of renting period
	 * @param nd End date of renting period
	 * @param f 'MasiniDisponibile' window
	 */
	  public DataActionListener(int idC, int idA, String st, String nd, JFrame f) {
	      this.start = st;
	      this.end = nd;
		  this.IDC = idC;
		  this.IDA = idA;
		  this.fr=f;
	  }
	  /**
	   * Class constructor if user is not logged in
	   * @param idC Value to show that user is not logged in
	   * @param F 'MasiniDisponibile' window
	   */
	  public DataActionListener(int idC, String st, String nd, JFrame F) { 
		  this.IDC = idC;
		  this.fr=F;
		  this.start=st;
		  this.end=nd;
	  }
	  
	  public DataActionListener(int IDa, boolean o) {
		  this.IDA=IDa;
		  this.ok=o;
	  }
	  
	  public DataActionListener(int IDa, int k, JFrame F) {
		  this.IDA=IDa;
		  this.okS=k;
		  this.fr=F;
	  }
	  public DataActionListener(int idP, int idA, String stP, JTextPane stR, float d, float pcl, float pi, float pt, String dc, JFrame f)
	  {
		  this.IDP=idP;
		  this.IDA=idA;
		  this.STP=stP;
		  this.STR=stR;
		  this.Daune=d;
		  this.PCL=pcl;
		  this.PI=pi;
		  this.PT=pt;
		  this.DC=dc;
		  this.fr=f;
		  this.okCO=1;
		  
	  }
	  public DataActionListener(JTextField pt, JTextField pa)
	  {
		 this.txt=pt;
		 this.txta=pa;
		 okTXT=1;
	  }
	  
	  /**
	   * This method closes 'MasiniDisponibile' window and redirdcts to another
	   * @param e Button pressed in 'MasiniDisponibile'
	   */
	  public void actionPerformed(ActionEvent e) {
		  if(ok)
		  {
			  new Detalii(IDA);
		  }
		  else if(IDC==-1) {
			  fr.dispose();
			  new Autentificare(start,end);
		  }
		  else if(okS==1)
		  {
			  Connection con = null;
	     		try {        			
	     			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
	     			String query= "DELETE FROM autoturisme WHERE ID='"+IDA+"'";                                                                                                 
	     			PreparedStatement st=con.prepareStatement(query);
	     			int update = st.executeUpdate();
	     			if(update>0)
	     			{
	     				JOptionPane.showMessageDialog(new JFrame(),
	     					    "Autoturismul a fost sters!");
	     				fr.dispose();
	     				new Administrator();
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
		  else if(okCO==1)
		  {
			  Connection con = null;
	     		try {        			
	     			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
	     			String query= "INSERT INTO checkout (ID_Programare, ID_Autoturism, StarePredare, StareReturnare, PretDaune, PretCombustibilLipsa, PretIntarzieri, PretTotal, DataCheckOut) VALUES (?,?,?,?,?,?,?,?,?)";                                                                                                 
	     			PreparedStatement st=con.prepareStatement(query);
	     			st.setInt(1, IDP);
	     			st.setInt(2, IDA);
	     			st.setString(3, STP);
	     			st.setString(4, STR.getText());
	     			st.setFloat(5, Daune);
	     			st.setFloat(6, PCL);
	     			st.setFloat(7, PI);
	     			st.setFloat(8, PT);
	     			st.setString(9, DC);
	     			int update = st.executeUpdate();
	     			String query2="UPDATE autoturisme SET Stare = '"+STR.getText()+"' WHERE ID='"+IDA+"'";
	     			PreparedStatement st2=con.prepareStatement(query2);
	     			st2.executeUpdate();
	     			query2="UPDATE programari SET CheckOut =1 WHERE ID='"+IDP+"'";
	     			st2=con.prepareStatement(query2);
	     			st2.executeUpdate();
	     			if(update>0)
	     			{
	     				JOptionPane.showMessageDialog(new JFrame(),
	     					    "Checkout finalizat!");
	     				fr.dispose();
	     				new Administrator();
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
		  else if(okTXT==1)
		  {
			  float pr=Float.parseFloat(txt.getText());
			  float add=Float.parseFloat(txta.getText());
			  txt.setText(Float.toString(pr+add));
		  }
		  else {
			  fr.dispose();
			  new SumarR(IDC, IDA, start, end);
		  }
	  }
	
}
