package gui.general;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HeaderPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage headerImg;
    private boolean imageLoaded;

    /**
     * Header of the application that displays the image of the header.
     */
    public HeaderPanel() {
       try {                
          headerImg = ImageIO.read(new File("src/resources/header.png"));
          imageLoaded = true;
       } catch (IOException ex) {
            System.out.println("wtf");
            imageLoaded = false;
       }
       this.setPreferredSize(new Dimension(
    		   (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 150));
    }

    /**
     * Draws the header image, or creates a JLabel if loading failed
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(imageLoaded) {
        	g.drawImage(headerImg, 0, 0, this); 
        }
        else {
        	JLabel headerTxt = new JLabel("FANTASY RPG");
        	this.add(headerTxt);
        }
    }

}