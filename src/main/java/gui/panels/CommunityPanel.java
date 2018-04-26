package gui.panels;

import backend.CurrentContext;
import backend.Player;
import backend.sql.GameServer;
import backend.sql.SQLActions.ListFriends;
import backend.sql.tables.Table;
import gui.general.ResultSetTable;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.general.ImagePanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

//Community, for viewing friends' profiles and searching for other players
public class CommunityPanel extends ImagePanel {
    private JScrollPane characterScroller;

    public CommunityPanel() {
        Label l = new Label("You have to login before you can view your friends!");
        characterScroller = new JScrollPane(l, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        characterScroller.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(characterScroller);
        characterScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void updateSQL() {
        Player player = CurrentContext.getPlayer();
        GameServer gs = CurrentContext.getGameServer();
         if(player == null || gs == null) {
            return;
        }
        String username = player.getUsername();
        System.out.println("Set community page for: "+username);
        ResultSet friends = gs.querry(new ListFriends(username));
        try {
            ResultSetTable rst = new ResultSetTable(friends);
            ImagePanel imagePanel = new ImagePanel();
            imagePanel.add(rst);
            characterScroller.setViewportView(imagePanel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
