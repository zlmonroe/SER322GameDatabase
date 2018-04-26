package gui.mainGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton homeB;
	private JButton profileB;
	private JButton charactersB;
	private JButton communityB;
	private JButton wikiB;
	private GridBagLayout layout;
	private MainPanel mainPanel;
	
	/**
	 * Creates the buttons on the side
	 * @param mp the main panel (with card layout) that the buttons switch
	 */
	public ButtonPanel(MainPanel mp) {
		mainPanel = mp;
		this.setBackground(new Color(28, 121, 25));
		
		layout = new GridBagLayout();
		Dimension buttonD = new Dimension(100, 100);
		homeB = new JButton("Home");
		homeB.setPreferredSize(buttonD);
		profileB = new JButton("My Profile");
		profileB.setPreferredSize(buttonD);
		charactersB = new JButton("My Characters");
		charactersB.setPreferredSize(buttonD);
		communityB = new JButton("Community");
		communityB.setPreferredSize(buttonD);
		wikiB = new JButton("Game Wiki");
		wikiB.setPreferredSize(buttonD);
		
		addListeners();
		GridBagConstraints con = new GridBagConstraints();

		this.setLayout(layout);
		con.gridx = 0;
		con.gridy = 0;
		con.weighty = .3;
		con.anchor = GridBagConstraints.PAGE_START;
		this.add(homeB, con);
		con.gridy = 1;
		con.weighty = 0.3;
		this.add(profileB, con);
		con.gridy = 2;
		con.weighty = 0.3;
		this.add(charactersB, con);
		con.gridy = 3;
		con.weighty = 0.3;
		this.add(communityB, con);
		con.gridy = 4;
		con.weighty = 1;
		this.add(wikiB, con);
		
		this.setSize(new Dimension(
				100, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 135));
	}
	
	private void addListeners() {
		homeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.switchPanel("HOME");
				System.out.println("button");
			}
		});
		
		profileB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.refresh();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.switchPanel("PROFILE");
			}
		});
		
		charactersB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.refresh();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.switchPanel("CHARACTERS");
			}
		});
		
		communityB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				mainPanel.switchPanel("COMMUNITY");
			}
		});
		
		wikiB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.switchPanel("WIKI");
			}
		});
	}
}
