package gui.panels;

import backend.CurrentContext;
import backend.Player;
import backend.sql.GameServer;
import backend.sql.SQLActions.JoinSearchQuery;
import backend.sql.SQLActions.ListFriends;
import backend.sql.SQLActions.SQLAction;
import backend.sql.tables.Table;
import gui.general.PromptTextField;
import gui.general.ResultSetTable;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.general.ImagePanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//Community, for viewing friends' profiles and searching for other players
public class CommunityPanel extends ImagePanel {
    private JScrollPane characterScroller;
    private PromptTextField name, item, quests, skills;

    public CommunityPanel() {
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setPreferredSize(new Dimension(999999,50));
        name = new PromptTextField("Friend");
        item = new PromptTextField("Friend's items");
        quests = new PromptTextField("Shared Quest");
        skills = new PromptTextField("Friend's skill");
        JButton go = new JButton("Run Search");
        go.addActionListener(e -> updateSQL());
        top.add(name);
        top.add(item);
        top.add(quests);
        top.add(skills);
        top.add(go);

        Label l = new Label("You have to login before you can view your friends!");
        characterScroller = new JScrollPane(l, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        characterScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
        characterScroller.setPreferredSize(new Dimension(9999999,500));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(top);
        this.add(characterScroller);
    }

    public void updateSQL() {
        Player player = CurrentContext.getPlayer();
        GameServer gs = CurrentContext.getGameServer();
         if(player == null || gs == null) {
            return;
        }
        String username = player.getUsername();
        System.out.println("Set community page for: "+username);


        String[] tables = new String[]{"PLAYERS", "FRIENDSWITH", "PLAYERCHAR",
                "HASITEM", "CHARACTERQUEST", "HASSKILL"};

        LinkedHashMap<String, String> joinConditions = new LinkedHashMap<>();
        joinConditions.put("PLAYERS.username","FRIENDSWITH.username");
        joinConditions.put("FRIENDSWITH.friendname","PLAYERCHAR.player");
        joinConditions.put("HASITEM.character", "PLAYERCHAR.charid");
        joinConditions.put("PLAYERCHAR.name","CHARACTERQUEST.playercharacter");
        joinConditions.put("PLAYERCHAR.charid","HASSKILL.character");

        LinkedHashMap<String, String> attrConditions = new LinkedHashMap<>();
        attrConditions.put("PLAYERS.username", username);

        LinkedList<String> popCols = new LinkedList<>();
        popCols.add("friendname");
        popCols.add("startdate");
        popCols.add("name");

        if(!Objects.equals(this.name.getEnteredText(), "")) {
            attrConditions.put("friendname", this.name.getEnteredText());
        }
        if(!Objects.equals(this.item.getEnteredText(), "")) {
            attrConditions.put("item", this.item.getEnteredText());
            popCols.add("item");
        }
        if(!Objects.equals(this.quests.getEnteredText(), "")) {
            attrConditions.put("quest", this.quests.getEnteredText());
            popCols.add("quest");
        }
        if(!Objects.equals(this.skills.getEnteredText(), "")) {
            attrConditions.put("skill", this.skills.getEnteredText());
            popCols.add("skill");
        }


        String[] searches = popCols.toArray(new String[popCols.size()]);
        System.out.println("YOUR SEARCHES:"+ Arrays.toString(searches));
        System.out.println("YOUR CONDITIONS:"+ Arrays.toString(joinConditions.keySet().toArray()));

        SQLAction jsq = new JoinSearchQuery(tables, joinConditions, attrConditions, searches,
                "friendname");
        ResultSet joinRs = gs.querry(jsq);
        System.out.println("join:"+joinRs);

        try {
            ResultSetTable rst = new ResultSetTable(joinRs);
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            rst.setOpaque(false);
            rst.setEnabled(false);
            panel.add(rst.getTableHeader(), BorderLayout.PAGE_START);
            panel.add(rst,BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            characterScroller.setViewportView(panel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
