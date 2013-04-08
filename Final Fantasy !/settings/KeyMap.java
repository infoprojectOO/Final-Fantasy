package settings;

import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class KeyMap extends InputMap {
	private static boolean predefined = true;
	
	public KeyMap() {
		super();
		if (predefined) {
			setDefault();
		}
		
	}
	
	public void setDefault() {
		this.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
		this.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		this.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		this.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");		
	}
	
	public static void customize() {
		
	}
}
