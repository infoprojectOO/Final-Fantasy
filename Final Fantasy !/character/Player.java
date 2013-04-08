package character;

import gui.Observer;

import item.IItem;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import add_on.GraphImage;

import world.IMapComponent;

public class Player implements IMapComponent {
	private BufferedImage appearance;
	private int posX;
	private int posY;
	private Map<String,IItem> inventory;
	private Observer display;

	public Player() {
		this.inventory = new HashMap<String,IItem>();
		this.appearance = GraphImage.getImage("Cloud_Strife_Nomura_art.jpg", this);
		
	}

	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public void addObserver(Observer o) {
		this.display = o;
	}
	
	public void move(int dx, int dy) {
		setPosition(this.posX + dx,this.posY + dy);
		this.display.update();
	}

	public Image getLook() {
		return this.appearance;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
}
