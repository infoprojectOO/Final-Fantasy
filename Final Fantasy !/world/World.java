package world;

import gui.Observer;

import java.util.HashMap;

import area.*;

import scenario.History;

import character.Player;

public class World {
	private HashMap<String,NetBoard> map;
	private Player explorer;
	private Observer display;

	public World() {
		this.explorer = new Player();
		this.map = new HashMap<String,NetBoard>();
		for (String s : History.PLACE_SERIES) {
			this.map.put(s,new NetBoard(s));
		}
	}

	public Player getPlayer() {
		return this.explorer;
	}

	public void addObserver(Observer o) {
		this.display = o;		
	}
	
	public PlayBoard getBoard() {
		return map.get(History.currentPlace()).getBoard();
	}
}
