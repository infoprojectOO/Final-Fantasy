package world;

import gui.GameDisplay;
import gui.Observer;

import java.util.HashMap;

import area.BattleField;

import scenario.History;

import character.Player;

public class World {
	private HashMap<String,PlayBoard> map;
	private Player explorer;
	private Observer display;

	public World() {
		this.explorer = new Player();
		this.map = new HashMap<String,PlayBoard>();
		for (String s : History.PLACE_SERIES) {
			this.map.put(s,new PlayBoard(20,20,this.explorer));
		} generate(this.map.get(History.currentPlace()));
	}

	private void generate(PlayBoard playboard) {
		playboard.put(new Building(),10,5);
		playboard.put(new BattleField(), 2, 15);
	}

	public Player getPlayer() {
		return this.explorer;
	}

	public void addObserver(Observer o) {
		this.display = o;		
	}
	
	public PlayBoard getBoard(String name) {
		return map.get(name);
	}
}
