package gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private CardLayout layout;
	
	private JPanel home;
	private JPanel profile;
	private JPanel characters;
	private JPanel community;
	private JPanel wiki;	
	
	public MainPanel() {
		layout = new CardLayout();
		this.setLayout(layout);
		
		home = new JPanel();
		profile = new JPanel();
		characters = new JPanel();
		community = new JPanel();
		wiki = new JPanel();
		
		this.add(home);
		this.add(profile);
	}
}
