package character;

import gui.Observer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player {
	private BufferedImage appearance;
	private int posX;
	private int posY;
	private List<Integer> position;
	private Observer display;

	public Player() {
		try {
			   URL url = Player.class.getResource("Cloud_Strife_Nomura_art.jpg");
			   this.appearance = ImageIO.read(url);
		} catch (IOException e) {System.out.println("No image found");}
		setPosition(50,50);
	}

	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public Image getAppearance() {
		return this.appearance;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

}
