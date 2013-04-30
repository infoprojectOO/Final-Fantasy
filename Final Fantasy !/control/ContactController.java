package control;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import convention.Axis;
import convention.Orientation;

import gui.BoardDisplay;
import character.IMotionCharacter;
import character.Player;
import world.EmptySlot;
import world.IMapComponent;
import world.PlayBoard;
import world.World;

public class ContactController {
	private Player motionplayer;
	private PlayBoard solidboard;
	/*private BoardDisplay collisionboard;*/
	private Point one=new Point(0,0),two=new Point(0,0); 
	
	public ContactController(World world) {
		this.motionplayer = world.getPlayer();
	}
	
	public boolean noContact() {
		boolean way = this.motionplayer.hasLeeway();
		if (!way) {
			way = true;
			Orientation arrow = this.motionplayer.getArrow();
			for (Point p : defineEdge(this.motionplayer)) {
				if (!this.solidboard.hasWay(arrow, p)) {
					System.out.println(p);
					return false;
				}
			}
		}
		return (way);
	}
	
	public boolean noContact(IMotionCharacter mobile) {
		boolean way = true;
		Orientation arrow = mobile.getArrow();
		for (Point p : defineEdge(mobile)) {
			if (!this.solidboard.hasWay(arrow, p)) {
				return false;
			}
		}
		return way;
	}
	
	private int findLevel(IMotionCharacter character) {
		Axis axis = character.getArrow().getAxis();
		int level = character.getPos(axis);/*/this.collisionboard.getScale(axis);*/
		if (character.getArrow()==Orientation.UP || character.getArrow()==Orientation.RIGHT) {
			level += character.getBreadth(axis)-1;
		} return level;
		
	}
	
	private ArrayList<Point> defineEdge(IMotionCharacter character) {
		Axis axis = character.getArrow().getAxis();
		int level = findLevel(character);		
		ArrayList<Integer> line = createEdge(character.getPos(axis.reverse())/*/this.collisionboard.getScale(axis.reverse())*/, 
				character.getBreadth(axis.reverse()));
		ArrayList<Point> edge = new ArrayList<Point>();
		for (int i : line) {
			if (axis==Axis.X) {
				edge.add(new Point(level,i));
			} else {
				edge.add(new Point(i,level));
			}
		} return edge;
	}

	private ArrayList<Integer> createEdge(int ini, int breadth) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int mid = ini+(breadth+1)/2-1;
		for (int i=0;i<=(breadth+1)/2-1;i++) {
			list.add(mid-i);
			list.add(mid+i+1);
		}
		if (list.get(list.size()-1)==ini+breadth) {
			list.remove(list.size()-1);
		} return list;
	}

	public void addDisplay(BoardDisplay board) {
		/*this.collisionboard = board;*/
	}

	public IMapComponent getTarget() {
		IMapComponent target = new EmptySlot();
		try {
			target = this.solidboard.getTarget(this.motionplayer.getArrow(), defineEdge(this.motionplayer).get(0));
		} catch (Exception e) {}
		return target;
	}


	public void changeBoard(PlayBoard boardmodel) {
		this.solidboard = boardmodel;		
	}

	public void allow(IMotionCharacter character) {
		if (noContact(character)) {
			this.solidboard.shift(character);
		}				
	}

}
