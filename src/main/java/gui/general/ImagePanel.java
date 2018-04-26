package gui.general;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Created by zlmonroe on 4/25/2018.
 */
public class ImagePanel extends JPanel {
    protected BufferedImage image;

    public ImagePanel() {
        try {
            image = ImageIO.read(new File("src/resources/Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
