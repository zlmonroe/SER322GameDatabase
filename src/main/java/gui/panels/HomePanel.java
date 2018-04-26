package gui.panels;

import backend.CurrentContext;
import gui.general.ImagePanel;
import gui.general.PromptTextField;
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
    PromptTextField password;
    String actualPassword;
    ArrayList<Character> holder;
    JButton login;
    BufferedImage image;

    public HomePanel() {
        username = new PromptTextField("Username");
        password = new PromptTextField("Password");
        actualPassword = "";
        holder = new ArrayList<>();

        username.setColumns(10);
        password.setColumns(10);
        login = new JButton("Login");
        loginAddListener();
        passwordKeyListener();
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
                    for(int i = 0; i < holder.size(); i++)
                        actualPassword += (""+holder.get(i));
                    CurrentContext.loginPlayer(username.getText(), actualPassword);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //resetting password
                holder = new ArrayList<>();
                actualPassword = "";

                //resetting login fields
                username.setText("Username");
                password.setText("Password");
            }
        });
    }

    private void passwordKeyListener(){
        password.addKeyListener(new KeyListener() {
            String tmp2 = "";
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (holder.size() > 0)
                        holder.remove(holder.size() - 1);
                }
                else {
                    String tmp = "";
                    holder.add(e.getKeyChar());
                    for(int i = 0; i < password.getText().length(); i++)
                        tmp += (""+'\u25CF');
                    password.setText(tmp);
                    tmp2 = tmp;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
               if(password.getText().length() != 0 && password.getText().charAt(password.getText().length()-1) != '\u25CF' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                   tmp2 += '\u25CF';
                   password.setText(tmp2);
               }
            }
        });
    }
}
