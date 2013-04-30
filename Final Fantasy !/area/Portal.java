package area;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import control.DisplayController;
import convention.Axis;

import add_on.GraphImage;

import world.PlayBoard;

public class Portal implements IArea {
	private BufferedImage look;
	private Dimension size;
	private PlayBoard in;
	private PlayBoard out;
	private Point inpoint;
	private Point outpoint;

	public Portal() {
		this.look = GraphImage.getImage("portal.jpg", this);
		this.size = new Dimension(10,10);
	}
	
	public Portal reversed() {
		Portal twin = new Portal();
		twin.setIn(out, outpoint);
		twin.setOut(in, inpoint);
		return twin;
	}

	@Override
	public Image getLook() {
		return this.look;
	}

/*	@Override
	public PlayBoard getDestination() {
		return this.out;
	}
	
	public Point getLocation() {
		return this.outpoint;
	}
*/
	public void setIn(PlayBoard playboard, Point loc) {
		this.in = playboard;
		this.inpoint = loc;		
	}

	public void setOut(PlayBoard playboard, Point loc) {
		this.out = playboard;
		this.outpoint = loc;		
	}

	@Override
	public void lead(DisplayController dispcontrol) {
		dispcontrol.teleport(this.out,this.outpoint);		
	}

	@Override
	public Dimension getSize() {
		return this.size;
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
	public void awaken() {		
	}

}
