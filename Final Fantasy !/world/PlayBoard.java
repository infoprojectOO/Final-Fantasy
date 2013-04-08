package world;
import gui.BoardDisplay;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.Move;
import add_on.GraphImage;
import area.IArea;

import character.Player;


public class PlayBoard {
	private  List<Map<Integer,IMapComponent>> map;
	private int width,height;
	private Player explorer;
	private Point coordinates;
	private BoardDisplay display;
	private BufferedImage background;
	private final static Integer[][] around = {{1,0},{0,1},{-1,0},{0,-1}};
	private final static List<String> ways = Arrays.asList("right","up","left","down");
	
	public PlayBoard(int width,int height,Player player) {
		if ((width | height) <= 0) {
			throw new RuntimeException("Negative Bounds");
		}
		else {
			this.height = height;
			this.width = width;
			this.background = GraphImage.getImage("final-fantasy-wallpaper.jpg", this);
			this.explorer = player;
			this.explorer.setPosition(0, 0);
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
	
	public void trackPlayer(int x, int y) {
		this.coordinates.x = x;
		this.coordinates.y = y;
	}

	public void addObserver(BoardDisplay board) {
		this.display = board;
		
	}

	public Player getPlayer() {
		return this.explorer;
	}

	public boolean hasWay(String direction) {
		int i = ways.indexOf(direction);
		int w = this.coordinates.x+around[i][0];
		int h = this.coordinates.y+around[i][1];
		boolean valid=false;
		try { 
			if(isEmpty(w,h)){
				valid = true;
			} else if(get(w,h) instanceof IArea){
				valid = true;
			}
		} catch (RuntimeException e) {System.out.println(" caught " + PlayBoard.ways.get(i));}
		return valid;
				
	}
}
