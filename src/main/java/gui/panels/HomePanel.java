package gui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
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
	BufferedImage image;
	
	public HomePanel() {
        this.setBackground(new Color(28,152,93));
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
        try {
            image = ImageIO.read(new File("src/resources/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    @Override
	public void paint(Graphics g) {
	    super.paint(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	    super.paintComponents(g);
	}

}
