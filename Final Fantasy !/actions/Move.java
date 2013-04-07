package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import world.PlayBoard;

public class Move extends AbstractAction {
	private int dx,dy;
	private PlayBoard actionground;
	
	public Move(int dx, int dy, PlayBoard actionground) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.actionground = actionground;
		
	}

	public Move(String arg0) {
		super(arg0);
	}

	public Move(String arg0, Icon arg1) {
		super(arg0, arg1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("here");
		this.actionground.movePlayer(dx,dy);
	}

}
