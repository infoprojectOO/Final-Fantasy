package area;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import character.IMotionCharacter;

import control.DisplayController;
import convention.Axis;

import add_on.GraphImage;

import world.BoardBox;
import world.IMapComponent;
import world.PlayBoard;

public class Portal extends Area {
	private BufferedImage look;
	private Dimension size;
	private PlayBoard in;
	private PlayBoard out;
	private Point inpoint;
	private Point outpoint;
	private BoardBox boardbox;

	public Portal(BoardBox boardbox) {
		this.look = GraphImage.getImage("portal.jpg", this);
		this.size = new Dimension(10,10);
		this.boardbox = boardbox;
	}
	
	public Portal reversed() {
		Portal twin = new Portal(this.boardbox);
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
//		dispcontrol.teleport(this.out,this.outpoint);		
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

	@Override
	public void stomped() {
		IMotionCharacter traveller = this.in.get(inpoint);
		traveller.setPosition(outpoint.x, outpoint.y);
		this.in.warpTo(this.out, traveller, outpoint);
		this.boardbox.upload(this.out);
	}

}
