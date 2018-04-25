package gui.panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.CurrentContext;
import backend.Player;
import backend.PlayerCharacter;
import backend.Skill;
//The characters of player who is logged int
public class MyCharactersPanel extends JPanel {
	private Player currentPlayer;

	public MyCharactersPanel() {
		List<String> charNames = new ArrayList<String>();
		charNames.add("t");
		charNames.add("p");
		for(String name : charNames) {
			this.add(createCharacterPanel(null));
		}
	}
	
	private JPanel createCharacterPanel(PlayerCharacter character) {
		JPanel charPanel = new JPanel();
		charPanel.setLayout(new GridBagLayout());
		charPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		GridBagConstraints con = new GridBagConstraints();
		
		charPanel.add(createBasicsPanel(character));
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
		
		if(character != null) {
			charT = new JTextField(character.getName());
			levT = new JTextField(character.getLevel());
			xpT = new JTextField(character.getXp());
		}
		else {
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
		
		charT.setEnabled(false);
		levT.setEnabled(false);
		xpT.setEnabled(false);

		con.insets = new Insets(0, 5, 5, 0);
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
	
	private JPanel createSkillsPanel(PlayerCharacter character) {
		JPanel charPanel = new JPanel();
		charPanel.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		JLabel skillL = new JLabel("Skills:");
		List<Skill> skills = new ArrayList<Skill>();
		skillL.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 20));
		con.insets = new Insets(0, 5, 5, 0);
		con.gridx = 0;
		con.gridy = 0;
		charPanel.add(skillL, con);
		
		for(Skill skill : skills) {
			JPanel skillPanel = new JPanel();
			skillPanel.setLayout(new GridBagLayout());
			GridBagConstraints conS = new GridBagConstraints();
			JLabel nameL = new JLabel("Name:");
			JTextField nameT;
			JLabel efL = new JLabel("Effect:");
			JTextField efT;
			
			if(character != null) {
				nameT = new JTextField(character.getLevel());
				efT = new JTextField(character.getXp());
			}
			else {
				nameT = new JTextField("NA");
				efT = new JTextField("NA");
			}
			
			nameT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 20));
			efT.setFont(new Font(skillL.getFont().getName(), Font.BOLD, 20));
			
			nameT.setEnabled(false);
			efT.setEnabled(false);

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
}
