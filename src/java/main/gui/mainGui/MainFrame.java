package gui.mainGui;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final List<String> PANEL_NAMES;
	private JPanel header;
	private MainPanel main;
	private JPanel buttons;
	
	static {
		String[] panelNames = {"HOME", "PROFILE", "CHARACTERS", "COMMUNITY", "WIKI"};
		PANEL_NAMES = Arrays.asList(panelNames);
	}
	
	public MainFrame() {
		this.setLayout(new BorderLayout());
		header = new HeaderPanel();
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
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}
