package gui.panels;

import backend.CurrentContext;
import backend.Player;
import backend.sql.GameServer;
import backend.sql.SQLActions.JoinSearchQuery;
import backend.sql.SQLActions.NoFailInsertFriends;
import backend.sql.SQLActions.SQLAction;
import gui.general.ImagePanel;
import gui.general.PromptTextField;
import gui.general.ResultSetTable;
import gui.general.SearchField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

//Community, for viewing friends' profiles and searching for other players
public class CommunityPanel extends ImagePanel {
    private JPanel top;
    private JScrollPane characterScroller;
    private PromptTextField name, item, quests, skills;
    private SearchField s;
    private JRadioButton friendButton, allButton;
    private JButton go;
    private ResultSet tmpPlayers;

    public CommunityPanel() {
        top = new JPanel();
        top.setMaximumSize(new Dimension(999999,10));
        name = new PromptTextField("Player");
        item = new PromptTextField("Item");
        quests = new PromptTextField("Quests");
        skills = new PromptTextField("Skill");
        go = new JButton("Run Search");
        go.addActionListener(e -> updateSQL());
        friendButton = new JRadioButton("Friends", true);
        allButton = new JRadioButton("All", false);
        ButtonGroup playerSearch = new ButtonGroup();
        playerSearch.add(friendButton);
        playerSearch.add(allButton);
        JPanel radioPanel = new JPanel();
        radioPanel.add(friendButton);
        radioPanel.add(allButton);
        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
         "Search only in my:"));
        top.add(radioPanel);
        top.add(name);
        top.add(item);
        top.add(quests);
        top.add(skills);
        top.add(go);

        s = new SearchField("PLAYERS", "username");
        JButton addFriend = new JButton("Add Friend");
        addFriend.addActionListener(e -> {
                if (CurrentContext.getPlayer() != null) {
                    GameServer gs = CurrentContext.getGameServer();
                    boolean errorFound = gs.execute(new NoFailInsertFriends(CurrentContext.getPlayer().getUsername(),
                            (String) s.getEditor().getItem()));
                    if(!errorFound){
                        JOptionPane.showMessageDialog(null, "The text you entered does relate to any existing player's name!",
                                "Player does not exist!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have to log in first!",
                            "Not yet logged in!",
                            JOptionPane.ERROR_MESSAGE);
                }
        });
        top.add(s);
        top.add(addFriend);

        Label l = new Label("You have to login before you can view your friends!");
        characterScroller = new JScrollPane(l, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        characterScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
//        characterScroller.setPreferredSize(new Dimension(9999999,550));
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
        if(friendButton.isSelected()) {
            joinConditions.put("PLAYERS.username", "FRIENDSWITH.username");
            joinConditions.put("FRIENDSWITH.friendname", "PLAYERCHAR.player");
        }
        else {
            joinConditions.put("PLAYERS.username", "PLAYERCHAR.player");
        }
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
                "friendname",false);
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
