package gui.panels;

import backend.CurrentContext;
import gui.general.PromptTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import javax.swing.JButton;

//Home landing page of application.
public class HomePanel extends ImagePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PromptTextField username;
	PromptTextField password;
	JButton login;
	BufferedImage image;

	public HomePanel() {
		username = new PromptTextField("Username");
		password = new PromptTextField("Password");
		username.setColumns(10);
		password.setColumns(10);
		login = new JButton("Login");
		loginAddListener();
		this.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		this.add(username, con);
		con.gridy = 1;
		this.add(password, con);
		con.gridy = 2;
		this.add(login, con);
    }
	
	private void loginAddListener() {
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					CurrentContext.loginPlayer(username.getText(), password.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
