package character;

import gui.Observer;

import item.MapItem;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;

import control.ContactController;
import convention.Access;
import convention.Axis;
import convention.Measurement;
import convention.Orientation;

import add_on.GraphImage;

public class Player implements IMotionCharacter {
//	private int counter;
//	private final HashMap<Orientation,BufferedImage[]> moves = new HashMap<Orientation,BufferedImage[]>() {
//		{
//			put(Orientation.DOWN,new BufferedImage[] {GraphImage.getImage("CloudU0.gif",this),GraphImage.getImage("CloudU1.gif",this),
//					GraphImage.getImage("CloudU2.gif",this),GraphImage.getImage("CloudU3.gif",this)}); 
//		}
//	};
	private BufferedImage look;
	private Dimension size;
	private Point position = new Point(0,0);
	private boolean[] leeway = {false,false};
	private Orientation arrow;
	private List<MapItem> inventory;
	private List<Heroes> protagonists;
	private Observer display;
	private Map<Orientation,Icon> moves;
	private ContactController contact;

	public Player() {
		this.inventory = new ArrayList<MapItem>();
		this.moves = new HashMap<Orientation,Icon>();
		this.moves.put(Orientation.UP,GraphImage.getIcon("CloudUp.gif", this));
		this.look = GraphImage.getImage("Cloud_Strife_Nomura_art.jpg", this);
		this.size = Measurement.PLAYER.getSize();
		this.arrow = Orientation.UP;		
	}

	@Override
	public void setPosition(int x, int y) {
		this.position = new Point(x,y);
	}
	
	@Override
	public void setArrow(Orientation arrow) {
		this.arrow = arrow;
	}
	
	@Override
	public Orientation getArrow() {
		return this.arrow;
	}
	
	public void setLeeway(Axis axis, boolean state) {
		if (axis==Axis.X) {
			this.leeway[0] = state;
		} else if (axis==Axis.Y) {
			this.leeway[1] = state;
		}
	}
	
	public void addObserver(Observer o) {
		this.display = o;
	}
	
	public void move(int dx, int dy) {
		this.position.translate(dx, dy);
		this.display.update();
	}

	public Image getLook() {
		return this.look;
	}
	
	public Icon getMove() {
		return this.moves.get(this.arrow);
	}

	public int getPos(Axis axis) {
		if (axis == Axis.X) {
			return this.position.x;
		} else {
			return this.position.y;
		}
	}

	public boolean hasLeeway() {
		if (this.arrow.getAxis()==Axis.Y) {
			return this.leeway[1];
		} else {
			return this.leeway[0];
		}
	}
	
	public boolean isCloistered() {
		return (!(this.leeway[0] || this.leeway[1]));
	}

	@Override
	public Integer getBreadth(Axis axis) {
		if (axis==Axis.X)
		{
			return (int) this.size.getWidth();
		} else {
			return (int) this.size.getHeight();
		}
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public void awaken() {
		System.out.println("Multiplayer ?");
	}

	@Override
	public boolean requestMove() {
		return this.contact.allow(this);
	}

	@Override
	public Access getAccessKey() {
		return Access.AREAKEY;
	}

	@Override
	public boolean allowsAccess(Access ackey) {
		return false;
	}

	public void setContact(ContactController contactcontrol) {
		this.contact = contactcontrol;
	}

	public void take(MapItem item) {
		this.inventory.add(item);
		this.contact.steal(item);
		this.display.update();
	}
	
}
