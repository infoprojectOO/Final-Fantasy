package control;

import java.awt.Point;

import gui.BoardDisplay;
import character.Player;
import world.EmptySlot;
import world.IMapComponent;
import world.PlayBoard;
import world.World;

public class ContactController {
	private Player motioncharacter;
	private PlayBoard solidboard;
	private BoardDisplay collisionboard;
	private Point one=new Point(0,0),two=new Point(0,0); 
	
	public ContactController(World world) {
		this.motioncharacter = world.getPlayer();	
		this.solidboard = world.getBoard();
	}
	
	public boolean noContact() {
		boolean leeway = this.motioncharacter.hasLeeway();
		boolean way = this.solidboard.hasWay(motioncharacter.getArrow(),one);
		if (!leeway && (!two.equals(one))) {
			way= (way && this.solidboard.hasWay(motioncharacter.getArrow(),two));
		}
		return (leeway || way);
	}

	public void splitSquare(Orientation axis, int loc) {
		float split = (float) this.motioncharacter.getPos(axis)/collisionboard.getScale(axis);
		Double l = Math.floor(split);
		int low = l.intValue();
		Double h = Math.ceil(split);
		int high = h.intValue();
		if (axis==Orientation.X) {
			one.setLocation(l,loc);
			two.setLocation(h,loc);
		} else if (axis==Orientation.Y) {
			one.setLocation(loc,l);
			two.setLocation(loc,h);
		}
	}

	public void addDisplay(BoardDisplay board) {
		this.collisionboard = board;
	}

	public IMapComponent getTarget() {
		IMapComponent target = new EmptySlot();
		if (one.equals(two)) {
			target = this.solidboard.getTarget(this.motioncharacter.getArrow(), one);						
		}
		return target;
	}

	public void changeBoard(PlayBoard boardmodel) {
		this.solidboard = boardmodel;		
	}

}
