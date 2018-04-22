package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;	
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
    private BufferedImage headerImg;
    private boolean imageLoaded;

    public HeaderPanel() {
       try {                
          headerImg = ImageIO.read(new File("src/resources/header.png"));
          imageLoaded = true;
       } catch (IOException ex) {
            System.out.println("wtf");
            imageLoaded = false;
       }
    }

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