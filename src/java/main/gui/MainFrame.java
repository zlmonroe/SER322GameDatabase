package gui;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	private JPanel header;
	private JPanel main;
	private JPanel buttons;
	
	private JButton homeB;
	private JButton profileB;
	private JButton charactersB;
	private JButton communityB;
	private JButton wikiB;
	
	public MainFrame() {
		this.setLayout(new BorderLayout());
		header = new HeaderPanel();
	}
	
	private void initMain() {
		
	}

}
