package world;
import gui.Observer;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.Orientation;

import add_on.GraphImage;
import area.IArea;

public class PlayBoard {
	private  List<Map<Integer,IMapComponent>> map;
	private int width,height;
	private Point coordinates;
	private BufferedImage background;
	private Observer display;
//	private final static Map<String,Integer[]> around = new HashMap<String,Integer[]>(); {
//		around.put("right", new Integer[] {1,0});
//		around.put("up", new Integer[] {0,1});
//		around.put("left", new Integer[] {-1,0});
//		around.put("down", new Integer[] {0,-1});
//	}

	
	public PlayBoard(int width,int height, String name) {
		if ((width | height) <= 0) {
			throw new RuntimeException("Negative Bounds");
		}
		else {
			this.height = height;
			this.width = width;
			this.background = GraphImage.getImage(name + ".jpg", this);
			this.coordinates = new Point(0,0);
			this.map = new ArrayList<Map<Integer,IMapComponent>>();
			for (int i=0;i<height;i++) {
				this.map.add(new HashMap<Integer,IMapComponent>());
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
		if (within(w,h)) {
			this.map.get(w).put(h, piece);	
		} else { throw new RuntimeException("Out of Bounds");
		}
	}
	
	public void remove(int w,int h) {
		if (!isEmpty(w,h)) {
			this.map.get(w).remove(h);
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

	public boolean hasWay(Orientation arrow, Point pos) {
		boolean valid=false;
		int w =	pos.x + arrow.getShifting()[0];
		int h = pos.y + arrow.getShifting()[1];
		try {
			if(isEmpty(w,h)){
				valid = true;
			} else if(get(w,h) instanceof IArea){
				valid = true;
			} 
		} catch (RuntimeException e) {}
		return valid;
	}
}
