package world;

import gui.Observer;

import java.util.HashMap;

import control.ChatBox;
import control.ContactController;

import area.*;

import scenario.History;

import character.Player;

public class World {
	private HashMap<String,NetBoard> map;
	private Player explorer;
	private Observer display;
	private ChatBox chatbox;
	private ContactController contact;
	
	public World() {
		this.chatbox = new ChatBox();
		this.explorer = new Player();
		this.contact = new ContactController(this);
		this.map = new HashMap<String,NetBoard>();
		for (String s : History.PLACE_SERIES) {
			this.map.put(s,new NetBoard(s,chatbox,contact));
		} this.contact.changeBoard(this.getBoard());
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

	public ChatBox getChatBox() {
		return this.chatbox;
	}

	public ContactController getContact() {
		return contact;
	}
}
