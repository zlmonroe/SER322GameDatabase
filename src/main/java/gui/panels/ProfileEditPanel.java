package gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.general.PromptTextField;
import gui.mainGui.MainPanel;
import backend.CurrentContext;
import backend.Player;
import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import gui.general.ImagePanel;

//Profile and account info of the player logged in
public class ProfileEditPanel extends ImagePanel {

    PromptTextField userNameF;
    PromptTextField passwordF;
    PromptTextField passwordConF;
    PromptTextField balanceF;
    PromptTextField oldPasswordF;
    Player player;
    GameServer gs = CurrentContext.getGameServer();
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ProfileEditPanel(MainPanel mp) throws IOException {
        this.setBackground(new Color(28,152,93));

        player = CurrentContext.getPlayer();
        
        JLabel oldPasswordL = new JLabel("Old Password:");

        JLabel userNameL= new JLabel("Username:");
        JLabel passwordL = new JLabel("New Password:");
        JLabel passwordConL = new JLabel("Confirm Password:");
        JLabel balanceL = new JLabel("Add to Balance:");

        if(player != null) {
            oldPasswordF = new PromptTextField("Old Password");
            
            userNameF = new PromptTextField(player.getUsername());
            passwordF = new PromptTextField("New Password");
            passwordConF = new PromptTextField("Confirm Password");
            balanceF = new PromptTextField("Deposit");
        }
        else {
            oldPasswordF = new PromptTextField("NA");
            userNameF = new PromptTextField("NA");
            passwordF= new PromptTextField("NA");
            passwordConF = new PromptTextField("NA");
            balanceF = new PromptTextField("NA");
        }

        oldPasswordF.setColumns(10);
        userNameF.setColumns(10);
        passwordF.setColumns(10);
        passwordConF.setColumns(10);
        balanceF.setColumns(10);
        
        oldPasswordF.setFont(new Font(userNameL.getFont().getName(), Font.BOLD, userNameL.getFont().getSize()));
        userNameF.setFont(new Font(userNameL.getFont().getName(), Font.BOLD, userNameL.getFont().getSize()));
        passwordF.setFont(new Font(userNameL.getFont().getName(), Font.BOLD, userNameL.getFont().getSize()));
        passwordConF.setFont(new Font(userNameL.getFont().getName(), Font.BOLD, userNameL.getFont().getSize()));
        balanceF.setFont(new Font(userNameL.getFont().getName(), Font.BOLD, userNameL.getFont().getSize()));
        
        JButton edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    editPlayer();
                } catch (HeadlessException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mp.switchPanel("PROFILE");
            }
        });

        JPanel accountInfo = new JPanel();

        GridBagConstraints con = new GridBagConstraints();
        accountInfo.setLayout(new GridBagLayout());
        con.gridx = 0;
        con.gridy = 0;
        con.anchor = GridBagConstraints.SOUTHWEST;
        con.insets = new Insets(5, 5, 0, 0);
        accountInfo.add(oldPasswordL, con);
        con.gridx = 1;
        con.gridy = 0;
        accountInfo.add(oldPasswordF, con);
        con.gridx = 2;
        con.gridy = 0;
        accountInfo.add(edit, con);
        con.gridx = 0;
        con.gridy = 2;
        accountInfo.add(userNameL, con);
        con.gridx = 1;
        con.gridy = 2;
        accountInfo.add(userNameF, con);
        con.gridx = 2;
        con.gridy = 2;
        accountInfo.add(cancel, con);
        con.gridx = 0;
        con.gridy = 3;
        accountInfo.add(passwordL, con);
        con.gridx = 1;
        con.gridy = 3;
        accountInfo.add(passwordF, con);
        con.gridx = 0;
        con.gridy = 4;
        accountInfo.add(passwordConL, con);
        con.gridx = 1;
        con.gridy = 4;
        accountInfo.add(passwordConF, con);
        con.gridx = 0;
        con.gridy = 5;
        accountInfo.add(balanceL, con);
        con.gridx = 1;
        con.gridy = 5;
        accountInfo.add(balanceF, con);

        BufferedImage myPicture = ImageIO.read(new File("src/resources/profile.png"));
        JLabel profImg = new JLabel(new ImageIcon(myPicture.getScaledInstance(230, 230, Image.SCALE_FAST)));
        profImg.setSize(new Dimension(230, 230));

        this.setLayout(new GridBagLayout());
        con.insets = new Insets(10, 10, 20, 20);
        con.gridx = 0;
        con.gridy = 0;
        this.add(profImg, con);
        con.gridx = 1;
        con.gridy = 0;
        this.add(accountInfo, con);
    }
    
    private void editPlayer() throws HeadlessException, SQLException {
        if(oldPasswordF.textEntered() && oldPasswordF.getEnteredText().equals(player.getPassword())) {
            if(userNameF.textEntered()) {
                ResultSet r =gs.querry(new GeneralQuery("Players", "username", userNameF.getEnteredText()));
                if(r.next()) {
                    JOptionPane.showMessageDialog(null,
                            "The username already exists. Please try again.", 
                            "Invalid Username", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    player.editPlayer("username", "'" + userNameF.getEnteredText() +"'");
                    player.setUsername(userNameF.getEnteredText());
                }
            }
            if(passwordF.textEntered() && passwordConF.textEntered() &&
                    passwordF.getEnteredText().equals(passwordConF.getEnteredText())) {
                player.setPassword(passwordF.getEnteredText());
                player.editPlayer("password", "'" + passwordF.getEnteredText() + "'");
            }
            else if(passwordF.textEntered() && passwordConF.textEntered()) {
                JOptionPane.showMessageDialog(null,
                        "The passwords you entered do not match. Please try again.", 
                        "Invalid Credentials",JOptionPane.ERROR_MESSAGE);
            }
            if(balanceF.textEntered()) {
                player.setBalance(Double.parseDouble(balanceF.getEnteredText()) + player.getBalance());
                player.editPlayer("balance", String.valueOf(player.getBalance()));
            }
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "The password you entered was invalid. Please try again.", 
                    "Invalid Credentials",JOptionPane.ERROR_MESSAGE);
        }
    }
}

