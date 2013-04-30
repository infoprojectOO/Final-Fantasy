package world;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.ChatBox;
import control.ContactController;
import convention.Measurement;

import character.MotionLooker;
import character.StillLooker;

import area.BattleField;
import area.Portal;
import area.SavePoint;

public class NetBoard {
	private Map<String,PlayBoard> net;
	private Map<String,List<String>> lines;
	private ChatBox chatbox;
	private ContactController contactcontrol;

	public NetBoard(String id, ChatBox chatbox, ContactController contact) {
		this.chatbox = chatbox;
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
		up.put(new SavePoint(), 60, 20);
		up.put(new MotionLooker(Measurement.LIVING_S,"Cait.gif",null, chatbox), 10, 45);
		up.put(new StillLooker(Measurement.LIVING_M,"Caetun.gif",chatbox), 80, 72);
		down.put(new Building(),100,50);
		down.put(new BattleField(), 20, 150);
		down.put(new Building(), 120, 150);
		net.put("mithra", up);
		net.put("zaneb", down);
	}

	private void link(PlayBoard p1, PlayBoard p2, Point loc1, Point loc2) {
		Portal line = new Portal();
		line.setIn(p1,loc1);
		line.setOut(p2,loc2);
		p1.put(line, loc1.x, loc1.y);
		p2.put(line.reversed(), loc2.x, loc2.y);
	}

}
