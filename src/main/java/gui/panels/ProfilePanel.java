package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.CurrentContext;
import backend.Player;
//Profile and account info of the player logged in
public class ProfilePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfilePanel(Player p) {
		JLabel myAccount = new JLabel("My Account") ;
		
		Player player = p;
		
		JLabel userNameL= new JLabel("Username:");
		JLabel startDateL = new JLabel("Start Date:");
		JLabel balanceL = new JLabel("Balance:");
		
		JTextField userNameF;
		JTextField startDateF;
		JTextField balanceF;
		
		if(player != null) {
			userNameF = new JTextField(player.getUsername());
			startDateF= new JTextField(player.getStartDate().toString());
			balanceF = new JTextField(String.valueOf(player.getBalance()));
		}
		else {
			userNameF = new JTextField("NA");
			startDateF= new JTextField("NA");
			balanceF = new JTextField("NA");
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		this.add(userNameL, con);
		con.gridx = 1;
		con.gridy = 0;
		this.add(userNameF, con);
		con.gridx = 0;
		con.gridy = 1;
		this.add(startDateL, con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(startDateF, con);
		con.gridx = 0;
		con.gridy = 2;
		this.add(balanceL, con);
		con.gridx = 1;
		con.gridy = 2;
		this.add(balanceF, con);
		
	}
}
