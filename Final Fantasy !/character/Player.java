package character;

import gui.Observer;

import item.IItem;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import control.Orientation;

import add_on.GraphImage;

import world.IMapComponent;

public class Player implements IMapComponent {
	private BufferedImage look;
	private int posX = 0, posY = 0;
	private boolean[] leeway = {false,false};
	private Orientation arrow;
	private Map<String,IItem> inventory;
	private Observer display;

	public Player() {
		this.inventory = new HashMap<String,IItem>();
		this.look = GraphImage.getImage("Cloud_Strife_Nomura_art.jpg", this);
		this.arrow = Orientation.UP;		
	}

	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public void setArrow(Orientation cst) {
		this.arrow = cst;
	}
	
	public void setLeeway(Orientation axis, boolean state) {
		if (axis==Orientation.X) {
			this.leeway[0] = state;
		} else if (axis==Orientation.Y) {
			this.leeway[1] = state;
		}
	}
	
	public void addObserver(Observer o) {
		this.display = o;
	}
	
	public void move(int dx, int dy) {
		setPosition(this.posX + dx,this.posY + dy);
		this.display.update();
	}

	public Image getLook() {
		return this.look;
	}

	public int getPos(Orientation axis) {
		if (axis == Orientation.X) {
			return this.posX;
		} else {
			return this.posY;
		}
	}
	
	public Orientation getArrow() {
		return arrow;
	}

	public boolean hasLeeway() {
		if (this.arrow.getAxis()==Orientation.Y.getAxis()) {
			return this.leeway[1];
		} else {
			return this.leeway[0];
		}
	}
	
	public boolean isCloistered() {
		return (!(this.leeway[0] || this.leeway[1]));
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
}
