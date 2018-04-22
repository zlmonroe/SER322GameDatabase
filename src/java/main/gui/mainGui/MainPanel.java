package gui.mainGui;

import java.awt.CardLayout;
import javax.swing.JPanel;

import backend.CurrentContext;
import gui.panels.*;

public class MainPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout layout;
	
	private JPanel home;
	private JPanel profile;
	private JPanel characters;
	private JPanel community;
	private JPanel wiki;
	
	/**
	 * Creates the panel for the center of the frame.
	 */
	public MainPanel() {		
		layout = new CardLayout();
		this.setLayout(layout);
		
		home = new HomePanel();
		profile = new ProfilePanel(CurrentContext.getPlayer());
		characters = new JPanel();
		community = new JPanel();
		wiki = new JPanel();
		
		this.add(home, "HOME");
		this.add(profile, "PROFILE");
		this.add(characters, "CHARACTERS");
		this.add(community, "COMMUNITY");
		this.add(wiki, "WIKI");
		
		layout.show(this, "HOME");
		
	}
	
	/**
	 * Switches the panel to display the proper panel.
	 * @param panelName name of the panel to switch to 
	 */
	public void switchPanel(String panelName) {
		if(MainFrame.PANEL_NAMES.contains(panelName)){
			layout.show(this, panelName);
		}
	}
}
