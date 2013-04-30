package add_on;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GraphImage {

	public static BufferedImage getImage(String name, Object source) {
		BufferedImage img = null;
		try {
			  URL url = source.getClass().getResource(name);
			  img = ImageIO.read(url);
		} catch (IOException e) {System.out.println("No image found");}
		return img;	
	}
	
	public static Icon getIcon(String name, Object source) {
		ImageIcon icon = null;
		try {
			URL url = source.getClass().getResource(name);
			icon = new ImageIcon(url);
		} catch (Exception e) {System.out.println("No icon found");}
		return icon;		
	}

}
