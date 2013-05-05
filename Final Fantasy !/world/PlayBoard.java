package world;
import gui.Observer;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import character.IMotionCharacter;
import character.Player;

import convention.Access;
import convention.Axis;
import convention.Orientation;

import add_on.GraphImage;
import area.Area;

public class PlayBoard {
	private List<SortedMap<Integer,IMapComponent>> map;
	private List<SortedMap<Integer, IMotionCharacter>> travelmap;
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
			this.chars = Collections.synchronizedList(new ArrayList<IMotionCharacter>());
			this.background = GraphImage.getImage(name + ".jpg", this);
			this.map = new ArrayList<SortedMap<Integer,IMapComponent>>();
			this.travelmap = new ArrayList<SortedMap<Integer,IMotionCharacter>>();
			this.roots = new HashMap<IMapComponent,Point>();
			for (int i=0;i<height;i++) {
				this.map.add(new TreeMap<Integer,IMapComponent>());
				this.travelmap.add(new TreeMap<Integer,IMotionCharacter>());
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
		} else { throw new RuntimeException("Out of Bounds");
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
			} else if (!isVacant(w,h)) {
				res = this.travelmap.get(w).get(h);				
			}
		} catch (RuntimeException e) {}
		return res;
	}
	
	public IMotionCharacter get(Point loc) {
		IMotionCharacter res = null;
		try { 
			if (!isVacant(loc.x, loc.y)) {
				res = this.travelmap.get(loc.x).get(loc.y);				
			}
		} catch (RuntimeException e) {}
		return res;
	}

	private boolean isVacant(int w, int h) {
		if (within(w,h)) {
			if (this.travelmap.get(w).get(h)==null) {
				return true;
			} else {
				return false;
			}
		} else { throw new RuntimeException("Out of Bounds");
		}
	}

	public void put(IMapComponent piece, int w, int h) {
		int pw = (int) piece.getBreadth(Axis.X);
		int ph = (int) piece.getBreadth(Axis.Y);
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
	
	public void put(IMotionCharacter mobile, int w, int h) {
		int pw = (int) mobile.getBreadth(Axis.X);
		int ph = (int) mobile.getBreadth(Axis.Y);
		if (isVacant(w,h) && isVacant(w+pw-1,h+ph-1)) {
			this.roots.put(mobile, new Point(w,h));
			for (int i=0;i<pw;i++) {
				for (int j=0;j<ph;j++) {
					this.travelmap.get(w+i).put(h+j, mobile);
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
			this.roots.remove(piece);
		}
	}
	
	public void remove(IMotionCharacter mobile) {
		if (this.roots.containsKey(mobile)) {
			Point pos = this.roots.get(mobile);
			int pw = (int) mobile.getSize().getWidth();
			int ph = (int) mobile.getSize().getHeight();
			for (int i=0;i<pw;i++) {
				for (int j=0;j<ph;j++) {
					this.travelmap.get(pos.x+i).remove(pos.y+j);
				}
			}
			this.roots.remove(mobile);
		}
	}

	public boolean within(int w,int h) {
		return (w<this.width && h<this.height && w>=0 && h>=0);
	}
	
	public boolean inside(int w,int h) {
		return (this.roots.get(get(w,h)).equals(new Point(w,h)));
	}


	public IMapComponent getTarget(Orientation arrow, Point pos) {
		int w = pos.x + arrow.getShifting()[0];
		int h = pos.y + arrow.getShifting()[1];
		return this.get(w, h);
	}

	public boolean hasWay(Orientation dir, Point pos, Access ackey) {
		boolean valid=false;
		int w =	pos.x + dir.getShifting()[0];
		int h = pos.y + dir.getShifting()[1];
		try {
			if(isEmpty(w,h) && isVacant(w,h)){
				valid = true;
			} else {
				valid = get(w,h).allowsAccess(ackey);
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
	
	public void addMotionCharacter(IMotionCharacter imc, int w, int h) {
		imc.setPosition(w,h);	
		this.chars.add(imc);
		this.put(imc, w, h);
	}

	public List<IMotionCharacter> getMotionCharacters() {
		return this.chars;
	}

	public void warpTo(PlayBoard out, IMotionCharacter imc, Point landing) {
		this.chars.remove(imc);
		this.remove(imc);
		out.put(imc, landing.x, landing.y);
	}

}
