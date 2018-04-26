package gui.panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.CurrentContext;
import backend.Item;
import backend.Player;
import backend.PlayerCharacter;
import backend.Skill;

//The characters of player who is logged int
public class MyCharactersPanel extends ImagePanel {
	private Player currentPlayer;
	

	public MyCharactersPanel() {
		currentPlayer = CurrentContext.getPlayer();
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		if (currentPlayer != null) {
			List<String> charNames = currentPlayer.getCharacters();
			for (String name : charNames) {
				p.add(createCharacterPanel(new PlayerCharacter(name)));
			}
		}
		JScrollPane s = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		s.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.add(s);
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
		
		return charPanel;
	}

	private JPanel createBasicsPanel(PlayerCharacter character) {
		JPanel charPanel = new JPanel();
		charPanel.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();

		JLabel charL = new JLabel("Charcter:");
		JTextField charT;
		JLabel levL = new JLabel("Level:");
		JTextField levT;
		JLabel xpL = new JLabel("Experience:");
		JTextField xpT;

		if (character != null) {
			charT = new JTextField(character.getName());
			levT = new JTextField(String.valueOf(character.getLevel()));
			xpT = new JTextField(String.valueOf(character.getXp()));
		} else {
			charT = new JTextField("NA");
			levT = new JTextField("NA");
			xpT = new JTextField("NA");
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
		charT.setEditable(false);
		levT.setEditable(false);
		xpT.setEditable(false);

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
		JTextField hpT;
		JLabel attackL = new JLabel("Attack:");
		JTextField attackT;
		JLabel sightL = new JLabel("Sight:");
		JTextField sightT;

		JLabel manaL = new JLabel("Mana:");
		JTextField manaT;
		JLabel defenseL = new JLabel("Defense:");
		JTextField defenseT;
		JLabel speedL = new JLabel("Speed:");
		JTextField speedT;

		if (character != null) {
			hpT = new JTextField(String.valueOf(character.getHp()));
			attackT = new JTextField(String.valueOf(character.getAttack()));
			sightT = new JTextField(String.valueOf(character.getSight()));

			manaT = new JTextField(String.valueOf(character.getMana()));
			defenseT = new JTextField(String.valueOf(character.getDefense()));
			speedT = new JTextField(String.valueOf(character.getSpeed()));
		} else {
			hpT = new JTextField("NA");
			attackT = new JTextField("NA");
			sightT = new JTextField("NA");

			manaT = new JTextField("NA");
			defenseT = new JTextField("NA");
			speedT = new JTextField("NA");
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

		hpT.setEditable(false);
		attackT.setEditable(false);
		sightT.setEditable(false);
		manaT.setEditable(false);
		defenseT.setEditable(false);
		speedT.setEditable(false);

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
			JTextField nameT;
			JLabel efL = new JLabel("Effect:");
			JTextField efT;

			if (character != null) {
				nameT = new JTextField(skill.getName());
				efT = new JTextField(skill.getEffect());
			} else {
				nameT = new JTextField("NA");
				efT = new JTextField("NA");
			}

			nameT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 16));
			efT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 16));

			nameT.setEditable(false);
			efT.setEditable(false);

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
		JTextField goldT;
		JLabel weightL = new JLabel("Weight:");
		JTextField weightT;
		JLabel maxWeightL = new JLabel("MaxWeight:");
		JTextField maxWeightT;

		JLabel itemL = new JLabel("Items:");

		if (character != null) {
			goldT = new JTextField(String.valueOf(character.getHp()));
			weightT = new JTextField(String.valueOf(character.getCurrentCarry()));
			maxWeightT = new JTextField(String.valueOf(character.getMaxCarryWeight()));
		} else {
			goldT = new JTextField("NA");
			weightT = new JTextField("NA");
			maxWeightT = new JTextField("NA");
		}

		inventoryL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 20));
		goldL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		weightL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		maxWeightL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		goldT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		goldT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		weightT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		weightT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		maxWeightT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		maxWeightT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
		itemL.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));

		goldT.setEditable(false);
		weightT.setEditable(false);
		maxWeightT.setEditable(false);

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

		List<Item> items = new ArrayList<Item>();
		for (Item item : items) {
			JPanel itemPanel = new JPanel();
			itemPanel.setLayout(new GridBagLayout());
			GridBagConstraints conS = new GridBagConstraints();

			JLabel nameL = new JLabel("Name:");
			JTextField nameT;
			JLabel weightIL = new JLabel("Weight:");
			JTextField weightIT;
			JLabel efL = new JLabel("Effect:");
			JTextField efT;

			if (character != null) {
				nameT = new JTextField(item.getName());
				weightT = new JTextField(String.valueOf(item.getWeight()));
				efT = new JTextField(item.getEffect());
			} else {
				nameT = new JTextField("NA");
				weightT = new JTextField("NA");
				efT = new JTextField("NA");
			}

			nameT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
			weightT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));
			efT.setFont(new Font(inventoryL.getFont().getName(), Font.BOLD, 16));

			nameT.setEditable(false);
			weightT.setEditable(false);
			efT.setEditable(false);

			conS.anchor = GridBagConstraints.WEST;
			conS.gridx = 0;
			conS.gridy = 1;
			itemPanel.add(nameL, con);
			conS.gridx = 1;
			conS.gridy = 1;
			itemPanel.add(nameT, con);
			conS.gridx = 1;
			conS.gridy = 1;
			itemPanel.add(weightL, con);
			conS.gridx = 2;
			conS.gridy = 1;
			itemPanel.add(weightT, con);
			conS.gridx = 3;
			conS.gridy = 1;
			itemPanel.add(efL, con);
			conS.gridx = 4;
			conS.gridy = 1;
			itemPanel.add(efT, con);

			con.gridx = 0;
			con.gridy++;
			charPanel.add(itemPanel, conS);
		}
		return charPanel;
	}
}
