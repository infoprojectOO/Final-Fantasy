package settings;

import gui.DoubleTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class KeyMap extends InputMap {
	public final static Map<String,Integer> default_keys = new HashMap<String,Integer>() {
		{
			put("down",KeyEvent.VK_DOWN);
			put("up",KeyEvent.VK_UP);
			put("left",KeyEvent.VK_LEFT);
			put("right",KeyEvent.VK_RIGHT);
			put("accept",KeyEvent.VK_F);
			put("refuse",KeyEvent.VK_D);
			put("menu",KeyEvent.VK_M);
		}
	};
	public final static Map<String,Integer> custom_keys = new HashMap<String,Integer>() {
		{
			putAll(default_keys);
		}
	};
	
	public KeyMap() {
		super();
		setMap();
		
	}
	
	public void setMap() {
		for (String keystr : custom_keys.keySet()) {
			this.put(KeyStroke.getKeyStroke(custom_keys.get(keystr),0), keystr);
		}
	}
	
	public static void customize(HashMap<String,Integer> map) {
		custom_keys.putAll(map);				
	}

}
