package gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import backend.CurrentContext;
import backend.Player;
import gui.general.ImagePane;
//Profile and account info of the player logged in
public class ProfilePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfilePanel(Player p) throws IOException {
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
			userNameF = new JTextField("NA",7);
			startDateF= new JTextField("NA",7);
			balanceF = new JTextField("NA",7);
		}
		userNameF.setEditable(false);
		startDateF.setEditable(false);
		balanceF.setEditable(false);
		
		JPanel accountInfo = new JPanel();

		GridBagConstraints con = new GridBagConstraints();
		accountInfo.setLayout(new GridBagLayout());
		con.gridx = 0;
		con.gridy = 0;
		con.anchor = GridBagConstraints.SOUTHWEST;
		con.insets = new Insets(5, 5, 0, 0);
		accountInfo.add(userNameL, con);
		con.gridx = 1;
		con.gridy = 0;
		accountInfo.add(userNameF, con);
		con.gridx = 0;
		con.gridy = 1;
		accountInfo.add(startDateL, con);
		con.gridx = 1;
		con.gridy = 1;
		accountInfo.add(startDateF, con);
		con.gridx = 0;
		con.gridy = 2;
		accountInfo.add(balanceL, con);
		con.gridx = 1;
		con.gridy = 2;
		accountInfo.add(balanceF, con);
		
		BufferedImage myPicture = ImageIO.read(new File("src/resources/profile.png"));
		JLabel profImg = new JLabel(new ImageIcon(myPicture.getScaledInstance(230, 230, Image.SCALE_FAST)));
		profImg.setSize(new Dimension(230, 230));
		
		JLabel desc = new JLabel("Description:");
		JTextArea description = new JTextArea("NA", 10,100);
		int descWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int descHeight = Toolkit.getDefaultToolkit().getScreenSize().height/6;
		description.setSize(new Dimension(descWidth, descHeight));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    description.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    description.setEditable(false);
		
		this.setLayout(new GridBagLayout());
		con.insets = new Insets(0, 0, 20, 20);
		con.gridx = 0;
		con.gridy = 0;
		this.add(profImg, con);
		con.gridx = 1;
		con.gridy = 0;
		this.add(accountInfo, con);
		con.insets = new Insets(0, 0, 10, 0);
		con.gridx = 0;
		con.gridy = 1;
		this.add(desc, con);
		con.gridwidth = Toolkit.getDefaultToolkit().getScreenSize().width/230;
		con.gridx = 0;
		con.gridy = 2;
		con.anchor = GridBagConstraints.NORTHWEST;
		this.add(description, con);
	}
}
