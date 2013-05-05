package world;

import item.Potion;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.ContactController;
import control.MotionController;
import convention.Measurement;

import character.IMotionCharacter;
import character.MotionLooker;
import character.StillLooker;
import chat.ChatBox;
import data.DataBox;

import area.BattleField;
import area.Portal;
import area.SavePoint;

public class NetBoard {
	private Map<String,PlayBoard> net;
	private Map<String,List<String>> lines;
	private ChatBox chatbox;
	private DataBox databox;
	private ContactController contactcontrol;

	public NetBoard(String id, ContactController contact) {
		this.chatbox = Box.chat;
		this.databox = Box.data;
		this.contactcontrol = contact;
		this.net = new HashMap<String,PlayBoard>();
		this.lines = new HashMap<String,List<String>>();
		switch (id) {
		case "Mithra" : generate1(); break;
		case "Towem" : generate2(); break;
		case "Tazul" : generate3(); break;
		}
	}
	
	public PlayBoard getBoard() {
		return net.get("mithra");
	}
	
	public PlayBoard getBoard(String name) {
		return net.get(name);
	}

	private void generate3() {
		// TODO Auto-generated method stub
		
	}

	private void generate2() {
		// TODO Auto-generated method stub
		
	}

	private void generate1() {
		PlayBoard up = new PlayBoard(100,100, "mithra");
		PlayBoard down = new PlayBoard(200,200,"zaneb");
		link(up,down,new Point(60,90),new Point(70,0));
		up.put(new Building(), 20, 50);
		up.put(new SavePoint(databox), 60, 20);
		up.addMotionCharacter(new MotionLooker(Measurement.LIVING_S,"Cait.gif",contactcontrol, chatbox), 10, 45);
		up.put(new StillLooker(Measurement.LIVING_M,"Caetun.gif",chatbox), 80, 72);
		down.put(new Building(),100,50);
		down.put(new Potion(Measurement.ITEM_M,"Potion.jpg",contactcontrol.getPlayer()), 22, 64);
		down.put(new BattleField(Box.battle), 20, 150);
		down.put(new Building(), 120, 150);
		net.put("mithra", up);
		net.put("zaneb", down);
	}

	private void link(PlayBoard p1, PlayBoard p2, Point loc1, Point loc2) {
		Portal line = new Portal(Box.board);
		line.setIn(p1,loc1);
		line.setOut(p2,loc2);
		p1.put(line, loc1.x, loc1.y);
		p2.put(line.reversed(), loc2.x, loc2.y);
	}

}
