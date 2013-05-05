package world;

import gui.Observer;

import java.util.HashMap;

import control.ContactController;

import area.*;

import scenario.History;

import character.Player;
import chat.ChatBox;
import data.DataBox;

public class World {
	private HashMap<String,NetBoard> map;
	private Player explorer;
	private Observer display;
	private ContactController contact;
	
	public World() {
		this.explorer = new Player();
		this.contact = new ContactController(this);
		this.map = new HashMap<String,NetBoard>();
		for (String s : History.PLACE_SERIES) {
			this.map.put(s,new NetBoard(s,contact));
		} this.contact.setBoard(this.getBoard());
		this.getBoard().put(this.explorer, 0, 0);
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

	public ContactController getContact() {
		return contact;
	}
}
