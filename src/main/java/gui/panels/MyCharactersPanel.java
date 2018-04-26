package gui.panels;

import backend.CharacterQuest;
import backend.CurrentContext;
import backend.Item;
import backend.Player;
import backend.PlayerCharacter;
import backend.Skill;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

//The characters of player who is logged int
public class MyCharactersPanel extends JPanel {
    private Player currentPlayer;


    public MyCharactersPanel() {
        currentPlayer = CurrentContext.getPlayer();
        JPanel p = new JPanel();
        if (currentPlayer != null) {
            List<String> charNames = currentPlayer.getCharacters();
            for (String name : charNames) {
                p.add(createCharacterPanel(new PlayerCharacter(name)));
            }
        }


        JScrollPane characterScroller = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        characterScroller.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(characterScroller);
        characterScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private JPanel createCharacterPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        charPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        GridBagConstraints con = new GridBagConstraints();
        con.anchor = GridBagConstraints.WEST;
        con.gridy = 0;
        con.gridx = 0;
        con.weighty = 1;
        con.fill = GridBagConstraints.VERTICAL;
        charPanel.add(createBasicsPanel(character), con);
        con.gridy = 1;
        charPanel.add(createSkillsPanel(character), con);
        con.gridy = 2;
        charPanel.add(createStatsPanel(character), con);
        con.gridy = 3;
        charPanel.add(createInventoryPanel(character), con);
        con.gridy = 4;
        charPanel.add(createQuestsPanel(character), con);
        con.gridy = 5;
        //charPanel.add(createLocationsPanel(character), con);

        return charPanel;
    }

    private JPanel createBasicsPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel charL = new JLabel("Charcter:");
        JLabel charT;
        JLabel levL = new JLabel("Level:");
        JLabel levT;
        JLabel xpL = new JLabel("Experience:");
        JLabel xpT;

        if (character != null) {
            charT = new JLabel(character.getName());
            levT = new JLabel(String.valueOf(character.getLevel()));
            xpT = new JLabel(String.valueOf(character.getXp()));
        } else {
            charT = new JLabel("NA");
            levT = new JLabel("NA");
            xpT = new JLabel("NA");
        }

        charL.setFont(new Font(charL.getFont().getName(), Font.BOLD, 24));
        levL.setFont(new Font(charL.getFont().getName(), Font.BOLD, 20));
        xpL.setFont(new Font(charL.getFont().getName(), Font.BOLD, 20));

        charT.setFont(new Font(charL.getFont().getName(), Font.BOLD, 24));
        levT.setFont(new Font(charL.getFont().getName(), Font.BOLD, 20));
        xpT.setFont(new Font(charL.getFont().getName(), Font.BOLD, 20));

        charT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        levT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        xpT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(charL, con);
        con.gridx = 1;
        con.gridy = 0;
        charPanel.add(charT, con);
        con.gridx = 0;
        con.gridy = 1;
        charPanel.add(levL, con);
        con.gridx = 1;
        con.gridy = 1;
        charPanel.add(levT, con);
        con.gridx = 2;
        con.gridy = 1;
        charPanel.add(xpL, con);
        con.gridx = 3;
        con.gridy = 1;
        charPanel.add(xpT, con);

        return charPanel;
    }

    private JPanel createStatsPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel statsL = new JLabel("Charcter:");

        JLabel hpL = new JLabel("Hit Points:");
        JLabel hpT;
        JLabel attackL = new JLabel("Attack:");
        JLabel attackT;
        JLabel sightL = new JLabel("Sight:");
        JLabel sightT;

        JLabel manaL = new JLabel("Mana:");
        JLabel manaT;
        JLabel defenseL = new JLabel("Defense:");
        JLabel defenseT;
        JLabel speedL = new JLabel("Speed:");
        JLabel speedT;

        if (character != null) {
            hpT = new JLabel(String.valueOf(character.getHp()));
            attackT = new JLabel(String.valueOf(character.getAttack()));
            sightT = new JLabel(String.valueOf(character.getSight()));

            manaT = new JLabel(String.valueOf(character.getMana()));
            defenseT = new JLabel(String.valueOf(character.getDefense()));
            speedT = new JLabel(String.valueOf(character.getSpeed()));
        } else {
            hpT = new JLabel("NA");
            attackT = new JLabel("NA");
            sightT = new JLabel("NA");

            manaT = new JLabel("NA");
            defenseT = new JLabel("NA");
            speedT = new JLabel("NA");
        }

        statsL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 20));

        hpL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        attackL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        sightL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        manaL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        defenseL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        speedL.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        hpT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        hpT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        attackT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        attackT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        sightT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        sightT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        manaT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        manaT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        defenseT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        defenseT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        speedT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        speedT.setFont(new Font(statsL.getFont().getName(), Font.BOLD, 16));
        
        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(statsL, con);

        con.gridx = 0;
        con.gridy = 1;
        charPanel.add(hpL, con);
        con.gridx = 1;
        con.gridy = 1;
        charPanel.add(hpT, con);
        con.gridx = 2;
        con.gridy = 1;
        charPanel.add(attackL, con);
        con.gridx = 3;
        con.gridy = 1;
        charPanel.add(attackT, con);
        con.gridx = 4;
        con.gridy = 1;
        charPanel.add(sightL, con);
        con.gridx = 5;
        con.gridy = 1;
        charPanel.add(sightT, con);

        con.gridx = 0;
        con.gridy = 2;
        charPanel.add(manaL, con);
        con.gridx = 1;
        con.gridy = 2;
        charPanel.add(manaT, con);
        con.gridx = 2;
        con.gridy = 2;
        charPanel.add(defenseL, con);
        con.gridx = 3;
        con.gridy = 2;
        charPanel.add(defenseT, con);
        con.gridx = 4;
        con.gridy = 2;
        charPanel.add(speedL, con);
        con.gridx = 5;
        con.gridy = 2;
        charPanel.add(speedT, con);

        return charPanel;
    }

    private JPanel createSkillsPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel skillL = new JLabel("Skills:");
        List<Skill> skills = new ArrayList<Skill>();
        skillL.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 20));

        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(skillL, con);

        for (Skill skill : skills) {
            JPanel skillPanel = new JPanel();
            skillPanel.setLayout(new GridBagLayout());
            GridBagConstraints conS = new GridBagConstraints();
            JLabel nameL = new JLabel("Name:");
            JLabel nameT;
            JLabel efL = new JLabel("Effect:");
            JLabel efT;

            if (character != null) {
                nameT = new JLabel(skill.getName());
                efT = new JLabel(skill.getEffect());
            } else {
                nameT = new JLabel("NA");
                efT = new JLabel("NA");
            }

            nameT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 16));
            efT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 16));

            conS.anchor = GridBagConstraints.WEST;
            conS.gridx = 0;
            conS.gridy = 1;
            skillPanel.add(nameL, con);
            conS.gridx = 1;
            conS.gridy = 1;
            skillPanel.add(nameT, con);
            conS.gridx = 2;
            conS.gridy = 1;
            skillPanel.add(efL, con);
            conS.gridx = 3;
            conS.gridy = 1;
            skillPanel.add(efT, con);

            con.gridx = 0;
            con.gridy++;
            charPanel.add(skillPanel, conS);
        }
        return charPanel;
    }

    private JPanel createInventoryPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel inventoryL = new JLabel("Inventory:");

        JLabel goldL = new JLabel("Gold:");
        JLabel goldT;
        JLabel weightL = new JLabel("Weight:");
        JLabel weightT;
        JLabel maxWeightL = new JLabel("MaxWeight:");
        JLabel maxWeightT;

        JLabel itemL = new JLabel("Items:");

        if (character != null) {
            goldT = new JLabel(String.valueOf(character.getMoney()));
            weightT = new JLabel(String.valueOf(character.getCurrentCarry()));
            maxWeightT = new JLabel(String.valueOf(character.getMaxCarryWeight()));
        } else {
            goldT = new JLabel("NA");
            weightT = new JLabel("NA");
            maxWeightT = new JLabel("NA");
        }

        inventoryL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 20));
        goldL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        weightL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        maxWeightL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        goldT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        weightT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        maxWeightT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
        itemL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));

        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(inventoryL, con);
        con.gridx = 0;
        con.gridy = 1;
        charPanel.add(goldL, con);
        con.gridx = 1;
        con.gridy = 1;
        charPanel.add(goldT, con);
        con.gridx = 2;
        con.gridy = 1;
        charPanel.add(weightL, con);
        con.gridx = 3;
        con.gridy = 1;
        charPanel.add(weightT, con);
        con.gridx = 4;
        con.gridy = 1;
        charPanel.add(maxWeightL, con);
        con.gridx = 5;
        con.gridy = 1;
        charPanel.add(maxWeightT, con);
        con.gridx = 0;
        con.gridy = 2;
        charPanel.add(itemL, con);

        List<Item> items = character.getItems();
        for (Item item : items) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new GridBagLayout());
            GridBagConstraints conS = new GridBagConstraints();

            JLabel nameL = new JLabel("Name:");
            JLabel nameT;
            JLabel weightIL = new JLabel("Weight:");
            JLabel weightIT;

            if (character != null) {
                nameT = new JLabel(item.getName());
                weightIT = new JLabel(String.valueOf(item.getWeight()));
            } else {
                nameT = new JLabel("NA");
                weightIT = new JLabel("NA");
            }
            
            nameL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
            weightIL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
            nameT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
            weightIT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));

            conS.anchor = GridBagConstraints.WEST;
            conS.insets = new Insets(0, 0, 10, 10);
            conS.gridx = 0;
            conS.gridy = 0;
            itemPanel.add(nameL, conS);
            conS.gridx = 1;
            conS.gridy = 0;
            itemPanel.add(nameT, conS);
            conS.insets = new Insets(0, 30, 10, 0);
            conS.gridx = 2;
            conS.gridy = 0;
            itemPanel.add(weightIL, conS);
            conS.insets = new Insets(0, 0, 10, 10);
            conS.gridx = 3;
            conS.gridy = 0;
            itemPanel.add(weightIT, conS);

            con.gridx = 0;
            con.gridy++;
            charPanel.add(itemPanel, con);
        }
        return charPanel;
    }

    private JPanel createQuestsPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel questL = new JLabel("Quests:");
        List<CharacterQuest> quests = character.getQuests();
        questL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 20));

        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(questL, con);
        System.out.println(quests);
        for (CharacterQuest quest : quests) {
            JPanel questPanel = new JPanel();
            questPanel.setLayout(new GridBagLayout());
            GridBagConstraints conS = new GridBagConstraints();
            JLabel nameL = new JLabel("Name:");
            JLabel nameT;
            JLabel statusL = new JLabel("Status:");
            JLabel statusT;

            if (character != null) {
                nameT = new JLabel(quest.getQuest());
                statusT = new JLabel(String.valueOf(quest.getStatus()));
            } else {
                nameT = new JLabel("NA");
                statusT = new JLabel("NA");
            }

            nameT.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            statusT.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            nameL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            statusL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            
            conS.anchor = GridBagConstraints.WEST;
            conS.insets = new Insets(0, 0, 0, 10);
            conS.gridx = 0;
            conS.gridy = 1;
            questPanel.add(nameL, conS);
            conS.gridx = 1;
            conS.gridy = 1;
            questPanel.add(nameT, conS);
            conS.insets = new Insets(0, 30, 0, 0);
            conS.gridx = 2;
            conS.gridy = 1;
            questPanel.add(statusL, conS);
            conS.insets = new Insets(0, 0, 0, 10);
            conS.gridx = 3;
            conS.gridy = 1;
            questPanel.add(statusT, conS);

            con.gridx = 0;
            con.gridy++;
            charPanel.add(questPanel, con);
        }
        return charPanel;
    }
    
    private JPanel createLocationsPanel(PlayerCharacter character) {
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JLabel questL = new JLabel("Quests:");
        List<CharacterQuest> quests = character.getQuests();
        questL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 20));

        con.anchor = GridBagConstraints.WEST;
        con.insets = new Insets(0, 10, 10, 0);
        con.gridx = 0;
        con.gridy = 0;
        charPanel.add(questL, con);
        System.out.println(quests);
        for (CharacterQuest quest : quests) {
            JPanel questPanel = new JPanel();
            questPanel.setLayout(new GridBagLayout());
            GridBagConstraints conS = new GridBagConstraints();
            JLabel nameL = new JLabel("Name:");
            JLabel nameT;
            JLabel statusL = new JLabel("Effect:");
            JLabel statusT;

            if (character != null) {
                nameT = new JLabel(quest.getQuest());
                statusT = new JLabel(String.valueOf(quest.getStatus()));
            } else {
                nameT = new JLabel("NA");
                statusT = new JLabel("NA");
            }

            nameT.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            statusT.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            nameL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            statusL.setFont(new Font(questL.getFont().getName(), Font.BOLD, 16));
            
            conS.anchor = GridBagConstraints.WEST;
            conS.gridx = 0;
            conS.gridy = 1;
            questPanel.add(nameL, conS);
            conS.gridx = 1;
            conS.gridy = 1;
            questPanel.add(nameT, conS);
            conS.insets = new Insets(0, 0, 10, 30);
            conS.gridx = 2;
            conS.gridy = 1;
            questPanel.add(statusL, conS);
            conS.insets = new Insets(0, 0, 10, 10);
            conS.gridx = 3;
            conS.gridy = 1;
            questPanel.add(statusT, conS);

            con.gridx = 0;
            con.gridy++;
            charPanel.add(questPanel, con);
        }
        return charPanel;
    }
}
