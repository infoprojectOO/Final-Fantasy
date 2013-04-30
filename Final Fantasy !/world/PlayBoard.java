package world;
import gui.Observer;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import character.IMotionCharacter;

import convention.Orientation;

import add_on.GraphImage;
import area.IArea;

public class PlayBoard {
	private  List<SortedMap<Integer,IMapComponent>> map;
	private int width,height;
	private BufferedImage background;
	private Observer display;
	private Map<IMapComponent,Point> roots;
	private List<IMotionCharacter> chars;
	
	public PlayBoard(int width,int height, String name) {
		if ((width | height) <= 0) {
			throw new RuntimeException("Negative Bounds");
		}
		else {
			this.height = height;
			this.width = width;
			this.background = GraphImage.getImage(name + ".jpg", this);
			this.map = new ArrayList<SortedMap<Integer,IMapComponent>>();
			this.roots = new HashMap<IMapComponent,Point>();
			for (int i=0;i<height;i++) {
				this.map.add(new TreeMap<Integer,IMapComponent>());
			}		
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeigth() {
		return this.height;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public boolean isEmpty (int w, int h) {
		if (within(w,h)) {
			if (this.map.get(w).get(h)==null) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException("Out of Bounds");
		}
		
	}
	
	public boolean isRoot(int w, int h) {
		return roots.containsValue(new Point(w,h));
	}

	public IMapComponent get(int w, int h) {
		IMapComponent res = new EmptySlot();
		try { 
			if (!isEmpty(w,h)) {
				res = this.map.get(w).get(h);
			} 
		} catch (RuntimeException e) {}
		return res;
	}

	public void put(IMapComponent piece, int w, int h) {
		int pw = (int) piece.getSize().getWidth();
		int ph = (int) piece.getSize().getHeight();
		if (isEmpty(w,h) && isEmpty(w+pw-1,h+ph-1)) {
			this.roots.put(piece,new Point(w,h));
			for (int i=0;i<pw;i++) {
				for (int j=0;j<ph;j++) {
					this.map.get(w+i).put(h+j, piece);
				}
			}
		} else { throw new RuntimeException("Out of Bounds");
		}
	}

	public void remove(IMapComponent piece) {
		if (this.roots.containsKey(piece)) {
			Point pos = this.roots.get(piece);
			int pw = (int) piece.getSize().getWidth();
			int ph = (int) piece.getSize().getHeight();
			for (int i=0;i<pw;i++) {
				for (int j=0;j<ph;j++) {
					this.map.get(pos.x+i).remove(pos.y+j);
				}
			}
			if (this.chars.contains(piece)) {
				this.chars.remove(piece);
			}
			this.roots.remove(piece);
		}
	}

	public boolean within(int w,int h) {
		return (w<this.width && h<this.height && w>=0 && h>=0);
	}
	
/*	public void trackPlayer(int x, int y) {
		if (x>=0) {this.coordinates.x = x;}
		if (y>=0) {this.coordinates.y = y;}
	}*/

	public void addObserver(Observer o) {
		this.display = o;
		
	}

	public IMapComponent getTarget(Orientation arrow, Point pos) {
		int w = pos.x + arrow.getShifting()[0];
		int h = pos.y + arrow.getShifting()[1];
		return this.get(w, h);
	}

	public boolean hasWay(Orientation dir, Point pos) {
		boolean valid=false;
		int w =	pos.x + dir.getShifting()[0];
		int h = pos.y + dir.getShifting()[1];
		try {
			if(isEmpty(w,h)){
				valid = true;
			} else if(get(w,h) instanceof IArea){
				valid = true;
			} 
		} catch (RuntimeException e) {}
		return valid;
	}
	
	public void shift(IMotionCharacter character) {
		Point root = this.roots.get(character);
		Orientation arrow = character.getArrow();
		this.remove(character);
		this.put(character, root.x + arrow.getShifting()[0], root.y + arrow.getShifting()[1]);
		character.move(arrow.getShifting()[0], arrow.getShifting()[1]);
	}
	
	public void addMotionCharacter(IMotionCharacter imc) {
		this.chars.add(imc);
	}

	public List<IMotionCharacter> getMotionCharacters() {
		return this.chars;
	}
}
