package gui.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.CurrentContext;
import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import backend.sql.TableConstants;
import gui.general.ImagePanel;
import gui.general.ResultSetTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

//Game wiki page, to search for game info such as quests, item, etc
public class GameWikiPanel extends ImagePanel {
    private JPanel choicesPanel;
    private JComboBox choices;
    private JButton queryBasic;
    private GameServer gs;
    private JScrollPane scroll;

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


        //loading selection options into combo box
        for(int i = 0; i < TableConstants.TABLES.length; i++)
            choices.addItem(TableConstants.TABLES[i]);

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
        ResultSet queriedResults = gs.querry(new GeneralQuery(""+choices.getSelectedItem()));
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
