package gui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import backend.CurrentContext;
import gui.general.PromptTextField;

//Home landing page of application.
public class HomePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PromptTextField username;
	PromptTextField password;
	JButton login;
	
	public HomePanel() {
        this.setBackground(new Color(28,152,93));
		username = new PromptTextField("Username");
		password = new PromptTextField("Password");
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
				CurrentContext.loginPlayer(username.getText(), password.getText());
			}
		});
	}
}
