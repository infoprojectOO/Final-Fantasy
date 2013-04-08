package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import control.ActionController;

import world.PlayBoard;

public class Move extends AbstractAction {
	private int dx,dy;
	private String id;
	private PlayBoard actionground;
	private ActionController actioncenter;
	
	public Move(String id,int dx, int dy, ActionController playcontrol) {
		super();
		this.id = id;
		this.dx = dx;
		this.dy = dy;
		this.actioncenter = playcontrol;
		this.actionground = playcontrol.getBoard();
		
	}

	public Move(String arg0) {
		super(arg0);
	}

	public Move(String arg0, Icon arg1) {
		super(arg0, arg1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (this.actionground.hasWay(this.id)){
			this.actioncenter.shift(dx, dy);
		}
	}

}
