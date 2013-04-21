package settings;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ActionMap;

import control.ActionController;
import control.Orientation;

import actions.*;

public class ActionsMap extends ActionMap {
	public final static Map<String,String> name_key_converter = new HashMap<String,String>() {
		{
			put("Move Up", "up");
			put("Move Down", "down");
			put("Move Right", "right");
			put("Move Left", "left");
			put("Accept", "accept");
			put("Cancel", "refuse");
			put("Menu", "menu");
		}
	};

	public ActionsMap(ActionController playcontrol) {
		super();
		this.put("down", new Move(Orientation.UP,0,10,playcontrol)); //display and board vertical coordinates are inverted
		this.put("up", new Move(Orientation.DOWN,0,-10,playcontrol));
		this.put("left", new Move(Orientation.LEFT,-10,0,playcontrol));
		this.put("right", new Move(Orientation.RIGHT,10,0,playcontrol));
		this.put("accept", new Choose(true,playcontrol));
		this.put("refuse", new Choose(false,playcontrol));
		this.put("menu", new Pause(true, playcontrol));
	}

}
