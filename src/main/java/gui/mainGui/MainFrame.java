package gui.mainGui;
import backend.CurrentContext;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final List<String> PANEL_NAMES;
	private final static String HEADER_IMG;
	private MainPanel main;
	private JPanel buttons;
	
	static {
		String[] panelNames = {"HOME", "PROFILE", "CHARACTERS", "COMMUNITY", "WIKI"};
		PANEL_NAMES = Arrays.asList(panelNames);
		
		HEADER_IMG = "src/resources/header.png";
	}
	
	public MainFrame() throws IOException {
		this.setLayout(new BorderLayout());
		BufferedImage myPicture = ImageIO.read(new File(HEADER_IMG));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 150, Image.SCALE_FAST)));
		picLabel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(picLabel);
		main = new MainPanel();
		buttons = new ButtonPanel(main);
		
		this.add(picLabel, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.WEST);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );;
		this.pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
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
