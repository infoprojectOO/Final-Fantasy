package settings;

import javax.swing.ActionMap;

import control.ActionController;

import actions.*;

public class ActionsMap extends ActionMap {

	public ActionsMap(ActionController playcontrol) {
		super();
		this.put("down", new Move("up",0,10,playcontrol)); //display and board vertical coordinates are inverted
		this.put("up", new Move("down",0,-10,playcontrol));
		this.put("left", new Move("left",-10,0,playcontrol));
		this.put("right", new Move("right",10,0,playcontrol));
		this.put("accept", new Choose(true));
		this.put("refuse", new Choose(false));
	}

}
