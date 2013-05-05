package character;

import java.awt.Point;
import java.util.Random;

import chat.ChatBox;

import control.ContactController;
import convention.*;

public class MotionLooker extends People implements IMotionCharacter {
	private Orientation arrow;
	private Random random = new Random();
	private Point position;
	private ContactController contact;

	public MotionLooker(Measurement mes, String name, ContactController contact, ChatBox chatbox) {
		super(mes,name, chatbox);
		this.contact = contact;
	}

	@Override
	public void setArrow(Orientation arrow) {
		this.arrow = arrow;
	}
	@Override
	public Orientation getArrow() {
		return this.arrow;
	}

	@Override
	public void setPosition(int x, int y) {
		this.position = new Point(x,y);
	}

	@Override
	public int getPos(Axis axis) {
		if (axis == Axis.X) {
			return this.position.x;
		} else {
			return this.position.y;
		}
	}

	@Override
	public boolean requestMove() {
		if (!talking) {
			int i = random.nextInt(4);
			Orientation[] arrs = {Orientation.UP,Orientation.DOWN,Orientation.LEFT,Orientation.RIGHT};
			this.setArrow(arrs[i]);
			this.contact.allow(this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void move(int dx, int dy) {
		this.position.translate(dx,dy);
	}

	@Override
	public Access getAccessKey() {
		return null;
	}

}
