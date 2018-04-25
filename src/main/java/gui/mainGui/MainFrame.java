package gui.mainGui;
import backend.CurrentContext;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.general.ImagePane;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final List<String> PANEL_NAMES;
	private JPanel header;
	private final static String HEADER_IMG;
	private MainPanel main;
	private JPanel buttons;
	
	static {
		String[] panelNames = {"HOME", "PROFILE", "CHARACTERS", "COMMUNITY", "WIKI"};
		PANEL_NAMES = Arrays.asList(panelNames);
		
		HEADER_IMG = "src/resources/header.png";
	}
	
	public MainFrame() {
		this.setLayout(new BorderLayout());
		header = new ImagePane(HEADER_IMG, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 150);
		main = new MainPanel();
		buttons = new ButtonPanel(main);
		
		this.add(header, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.WEST);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	    if(args.length == 1) {
	        System.out.println("Connecting with password: "+args[0]);
            CurrentContext.startGameServer(args[0]);
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
            return;
	    }
	    System.out.println("Please call with your password as the only arg to test the " +
                "application.");

	}
}
