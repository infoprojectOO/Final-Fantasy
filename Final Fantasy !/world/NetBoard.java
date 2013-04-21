package world;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import area.BattleField;
import area.Portal;
import area.SavePoint;

public class NetBoard {
	private Map<String,PlayBoard> net;
	private Map<String,List<String>> lines;

	public NetBoard(String id) {
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
		PlayBoard up = new PlayBoard(10,10, "mithra");
		PlayBoard down = new PlayBoard(20,20,"zaneb");
		link(up,down,new Point(6,9),new Point(7,0));
		up.put(new Building(), 2, 5);
		up.put(new SavePoint(), 6, 2);
		down.put(new Building(),10,5);
		down.put(new BattleField(), 2, 15);
		down.put(new Building(), 12, 15);
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
