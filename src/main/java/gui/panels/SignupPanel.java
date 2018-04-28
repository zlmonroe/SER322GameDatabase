package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import backend.CurrentContext;
import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import gui.general.ImagePanel;
import gui.general.PasswordTextField;
import gui.general.PromptTextField;
import gui.mainGui.MainPanel;

public class SignupPanel extends ImagePanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    PromptTextField username;
    PromptTextField password;
    PromptTextField password2;
    JButton cancel;
    JButton signup;
    private GameServer gs;
    
    public SignupPanel(MainPanel main) {
        gs = CurrentContext.gameServer;
        setLayout(new GridBagLayout());
        username = new PromptTextField("Username");
        username.setTransparent(true);
        username.setColumns(10);
        password = new PasswordTextField("Password");
        password.setTransparent(true);
        password.setColumns(10);
        password2 = new PasswordTextField("Reenter Password");
        password2.setTransparent(true);
        password2.setColumns(10);
        cancel = new JButton("Cancel");
        signup = new JButton("Sign Up");
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        add(username, con);
        con.gridy = 1;
        add(password, con);
        con.gridy = 2;
        add(password2, con);
        con.gridy = 3;
        con.gridx = 0;
        con.gridwidth = 1;  
        add(signup, con);   
        con.gridx = 1;   
        add(cancel, con);
        
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ResultSet r =gs.querry(new GeneralQuery("Players", "username", username.getEnteredText()));
                try {
                    if(!password.getEnteredText().equals(password2.getEnteredText())||r.next()) {
                        JOptionPane.showMessageDialog(null,
                                "The passwords entered do not match or username already exists. Please try again.", 
                                "Invalid Passwords", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        CurrentContext.signupPlayer(username.getEnteredText(), password.getEnteredText());
                        main.refresh();
                        main.switchPanel("PROFILE");
                    }
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //resetting login fields
                username.setText("Username");
                password.setText("Password");
                password2.setText("Reenter Password");
            }
        });
        
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.switchPanel("HOME");
            }
        });
    }

}
