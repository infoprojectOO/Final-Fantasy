package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import control.ActionController;

public class Choose extends AbstractAction {
	private boolean accept;
	private ActionController actioncenter;
	
	public Choose(boolean state, ActionController playcontrol) {
		this.accept = state;
		this.actioncenter = playcontrol;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("accept");
		this.actioncenter.undertake(this.accept);		
	}
}
