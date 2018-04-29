package gui.panels;

import backend.CurrentContext;
import gui.general.ImagePanel;
import gui.general.PasswordPromptField;
import gui.general.PromptTextField;
import gui.mainGui.MainPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

//Home landing page of application.
public class HomePanel extends ImagePanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    PromptTextField username;
    PasswordPromptField password;
    JButton login;
    JButton signup;
    BufferedImage image;
    MainPanel main;

    public HomePanel(MainPanel mp) {
        main = mp;
        username = new PromptTextField("Username");
        password = new PasswordPromptField("Password");

        username.setTransparent(true);
        password.setTransparent(true);

        username.setColumns(10);
        password.setColumns(10);
        login = new JButton("Login");
        signup = new JButton("Sign Up");
        loginAddListener();
        signupAddListener();
        this.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        this.add(username, con);
        con.gridy = 1;
        this.add(password, con);
        con.gridy = 2;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.EAST;
        this.add(login, con);
        con.gridx = 1;
        this.add(signup, con);
    }

    private void loginAddListener() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(!CurrentContext.loginPlayer(username.getEnteredText(), password.getEnteredText())) {
                        JOptionPane.showMessageDialog(null,
                                "The username and/or password you entered was invalid. Please try again.", 
                                "Invalid Credentials",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        main.switchPanel("PROFILE");
                        //resetting login fields
                        username.setText("Username");
                        password.setPrompt("Password");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void signupAddListener(){
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.switchPanel("SIGNUP");
            }
        });
    }
}
