package actions;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class Exit extends AbstractAction {
	public static Window window;

	public Exit() {}
	
	public Exit(String name) {
		super(name);
	}

	public static void setWindow(Window w) {
		Exit.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Exit.window.dispose();
	}

}
