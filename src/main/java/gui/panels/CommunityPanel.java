package gui.panels;

import backend.CurrentContext;
import backend.Player;
import backend.sql.GameServer;
import backend.sql.SQLActions.ListFriends;
import backend.sql.tables.Table;
import gui.general.ResultSetTable;
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
    private ResultSetTable rst;

    public CommunityPanel() {
        ResultSetTable rst = null;
        JScrollPane characterScroller = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        characterScroller.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(characterScroller);
        characterScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void update() {
        Player player = CurrentContext.getPlayer();
        GameServer gs = CurrentContext.getGameServer();
        String username = player.getUsername();
        ResultSet friends = gs.querry(new ListFriends(username));
        try {
            rst.setModel(ResultSetTable.buildTableModel(friends));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
