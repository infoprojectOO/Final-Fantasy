package world;
import gui.BoardDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import character.Player;


public class PlayBoard {
	private  ArrayList<Map<Integer,IMapComponent>> map;
	private int width,height;
	private Player explorer;
	private BoardDisplay display;
	
	public PlayBoard(int width,int height, Player player) {
		if ((width | height) <= 0) {
			throw new RuntimeException("Negative Bounds");
		}
		else {
			this.height = height;
			this.width = width;
			this.explorer = player;
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

	public boolean isEmpty(int w, int h) {
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
		if (!isEmpty(w,h)) {
			return this.map.get(w).get(h);
		} else {
			return (new EmptySlot());
		}
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

	public void movePlayer(int dx, int dy) {
		this.explorer.setPosition(this.explorer.getPosX()+dx,this.explorer.getPosY()+dy);
		this.display.update();
	}

	public void addObserver(BoardDisplay board) {
		this.display = board;
		
	}
}
