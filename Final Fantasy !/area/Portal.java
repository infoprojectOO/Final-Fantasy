package area;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.DisplayController;

import add_on.GraphImage;

import world.PlayBoard;

public class Portal implements IArea {
	private BufferedImage look;
	private PlayBoard in;
	private PlayBoard out;
	private Point inpoint;
	private Point outpoint;

	public Portal() {
		this.look = GraphImage.getImage("portal.jpg", this);
	}
	
	public Portal reversed() {
		Portal twin = new Portal();
		twin.setIn(out, outpoint);
		twin.setOut(in, inpoint);
		return twin;
	}

	@Override
	public boolean isActive() {
		return false;
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

}
