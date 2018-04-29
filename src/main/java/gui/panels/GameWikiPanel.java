package gui.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.CurrentContext;
import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import backend.sql.SQLActions.JoinSearchQuery;
import backend.sql.TableConstants;
import gui.general.ImagePanel;
import gui.general.ResultSetTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
//Game wiki page, to search for game info such as quests, item, etc
public class GameWikiPanel extends ImagePanel {
    private JPanel choicesPanel;
    private JComboBox choices;
    private JButton queryBasic;
    private GameServer gs;
    private JScrollPane scroll;
    private LinkedHashMap<String, String[]> tables;
    private LinkedHashMap<String, String[]> attrs;

    public GameWikiPanel(){
        choicesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        choicesPanel.setMaximumSize(new Dimension(3000, 50));
        choicesPanel.setMinimumSize(new Dimension(3000, 50));
        choicesPanel.setPreferredSize(new Dimension(3000, 50));
        choicesPanel.setBackground(Color.GREEN);
        choices = new JComboBox();
        queryBasic = new JButton("View");
        gs = CurrentContext.getGameServer();

        //formatting scroll pane
        Label l = new Label("You have to login before you can view video game content!");
        scroll = new JScrollPane(l, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setAlignmentX(CENTER_ALIGNMENT);
        scroll.setAlignmentY(choicesPanel.getAlignmentY());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));

        tables = new LinkedHashMap();
        tables.put("Skills",new String[]{"SKILL","SKILLEFFECT"});
        tables.put("Locations", new String[]{"LOCATIONS"});
        tables.put("Effects",new String[]{"EFFECT"});
        tables.put("Quests", new String[]{"QUEST","QUESTLOCATION"});
        tables.put("Characters", new String[]{"NONPLAYERCHAR"});
        tables.put("Items", new String[]{"ITEM","ITEMEFFECT","CONSUMABLE","ARMOR","WEAPON"});

        attrs = new LinkedHashMap<>();
        attrs.put("SKILL",new String[]{"name","level","cooldown","manacost"});
        attrs.put("SKILLEFFECT",new String[]{"effect"});
        attrs.put("QUEST",new String[]{"name","minlevel","moneyreward","xpreward","itemreward","npc"});
        attrs.put("QUESTLOCATION",new String[]{"location"});
        attrs.put("ITEM",new String[]{"name","effectmultiplier","weight"});
        attrs.put("ITEMEFFECT",new String[]{"effect"});
        attrs.put("CONSUMABLE",new String[]{"cooldown"});
        attrs.put("ARMOR",new String[]{"defense"});
        attrs.put("WEAPON",new String[]{"damage"});

        String[] listOptions = tables.keySet().toArray(new String[tables.keySet().size()]);

        //loading selection options into combo box
        for(int i = 0; i < tables.size(); i++)
            choices.addItem(listOptions[i]);

        //adding button listener
        queryBasic.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryBasic_actionPerformed(e);
            }
        });

        //formatting basic query button
        queryBasic.setMaximumSize(new Dimension(100, 26));
        queryBasic.setMinimumSize(new Dimension(100, 26));
        queryBasic.setPreferredSize(new Dimension(100, 26));
        choicesPanel.add(queryBasic);

        //formatting combo box and its label
        JLabel choiceLabel = new JLabel();
        choiceLabel.setText("Content:");
        choicesPanel.add(choiceLabel);
        choices.setMaximumSize(new Dimension(200, 26));
        choices.setMinimumSize(new Dimension(200, 26));
        choices.setPreferredSize(new Dimension(200, 26));
        choicesPanel.add(choiceLabel);
        choicesPanel.add(choices);


        //adding the query button and choices combo box
        this.add(choicesPanel,BorderLayout.NORTH);

        //setting view of scrollpane to be image
        ImagePanel background = new ImagePanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        scroll.setViewportView(background);
        this.add(scroll,BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    private void queryBasic_actionPerformed(ActionEvent e){

        //loading join conditions in LHM
        LinkedHashMap<String,String> jCon = new LinkedHashMap<>();
        String[] tmp = tables.get(""+choices.getSelectedItem());
        ArrayList<String> attrTmp = new ArrayList<>();

        //loading attributes to select from schemas
        if(tmp.length == 1) {
            attrTmp.add("*");
        }
        else {
            for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < attrs.get(tmp[i]).length; j++) {
                    attrTmp.add(tmp[i] + "." + attrs.get(tmp[i])[j]);
                }
            }
        }
        if(tmp.length > 1) {
            if(tmp[0].equals("ITEM")) {
                jCon.put(tables.get("" + choices.getSelectedItem())[0] + ".name", tables.get("" + choices.getSelectedItem())[1] + ".item");
                jCon.put(tables.get("" + choices.getSelectedItem())[1] + ".item", tables.get("" + choices.getSelectedItem())[2] + ".name");
                for (int i = 2; i < tmp.length-1; i++)
                    jCon.put(tables.get("" + choices.getSelectedItem())[i] + ".name", tables.get("" + choices.getSelectedItem())[i+1] + ".name");
            }
            else{
                for (int i = 0; i < tmp.length; i++)
                    jCon.put(tables.get("" + choices.getSelectedItem())[0] + ".name", tables.get("" + choices.getSelectedItem())[i] + "." + tmp[0].toLowerCase());
            }
        }

        ResultSet queriedResults = gs.querry(new JoinSearchQuery(tmp
                ,jCon
                ,null
                , attrTmp.toArray(new String[attrTmp.size()])
                ,tmp[0] + ".name",true));
        try {
            ResultSetTable results = new ResultSetTable(queriedResults);
            ImagePanel refreshedView = new ImagePanel();
            refreshedView.add(results.getTableHeader(), BorderLayout.PAGE_START);
            refreshedView.add(results,BorderLayout.CENTER);
            refreshedView.setLayout(new BoxLayout(refreshedView, BoxLayout.PAGE_AXIS));
            scroll.setViewportView(refreshedView);
            scroll.revalidate();
            scroll.repaint();
            System.out.println("Shalom");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
