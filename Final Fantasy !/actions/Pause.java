package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import control.ActionController;

public class Pause extends AbstractAction {
	private boolean skipsave;
	private ActionController actioncenter;
	
	public Pause(boolean menu, ActionController ac) {
		super();
		this.skipsave = !menu;		
		this.actioncenter = ac;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.actioncenter.menu(skipsave);
	}

}
