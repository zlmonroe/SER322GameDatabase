package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import backend.CurrentContext;
import gui.general.ImagePanel;
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
    
    public SignupPanel(MainPanel main) {
        setLayout(new GridBagLayout());
        username = new PromptTextField("Username");
        password = new PromptTextField("Password");
        password2 = new PromptTextField("Reenter Password");
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
        add(cancel, con);
        con.gridx = 1;
        add(signup, con);
        
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!password.getEnteredText().equals(password2.getEnteredText())) {
                    JOptionPane.showMessageDialog(null,
                            "The passwords entered do not match. Please try again.", 
                            "Invalid Passwords", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    CurrentContext.signupPlayer(username.getEnteredText(), password.getEnteredText());
                }
                //resetting login fields
                username.setText("Username");
                password.setText("Password");
                main.switchPanel("PROFILE");
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
