package actions;

import gui.RootLayer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Navigate extends AbstractAction {
	private static RootLayer navigator; 

	public Navigate() {
		super();
	}
	
	public Navigate(String text) {
		super(text);
	}

	public static void setNavigator(RootLayer nav) {
		Navigate.navigator = nav;
	}

	@Override
	public void actionPerformed(ActionEvent prev) {
		this.navigator.removeLayer(0);
		this.navigator.getComponent(0).setEnabled(true);
	}

}
