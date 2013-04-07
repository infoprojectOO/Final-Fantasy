package settings;

import javax.swing.ActionMap;

import actions.*;

import world.PlayBoard;

public final class ActionsMap extends ActionMap {

	public ActionsMap(PlayBoard actionground) {
		super();
		this.put("down", new Move(0,10,actionground));
		this.put("up", new Move(0,-10,actionground));
		this.put("left", new Move(-10,0,actionground));
		this.put("right", new Move(10,0,actionground));
	}

}
