package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import control.ActionController;
import convention.Axis;
import convention.Orientation;

public class Move extends AbstractAction {
	private int dx,dy;
	private Orientation id;
	private ActionController actioncenter;
	
	public Move(Orientation dir,int dx, int dy, ActionController playcontrol) {
		super();
		this.id = dir;
		this.dx = dx;
		this.dy = dy;
		this.actioncenter = playcontrol;		
	}

	public Move(String arg0) {
		super(arg0);
	}

	public Move(String arg0, Icon arg1) {
		super(arg0, arg1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		this.actioncenter.shift(dx, dy, id);
	}

}
