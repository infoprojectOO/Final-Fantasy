package settings;

import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class KeyMap extends InputMap {
	private final static String[] specials = {"down","up","left","right"};
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
	private static Map<String,Integer> temporary_keys;
	
	public KeyMap() {
		super();
		setMap();
	}
	
	public void setMap() {
		for (String keystr : custom_keys.keySet()) {
			this.put(KeyStroke.getKeyStroke(custom_keys.get(keystr),0), keystr);
		} for (String spc : specials) {
			this.put(KeyStroke.getKeyStroke(custom_keys.get(spc),0,true), spc+" stop");
		}
		
	}
	
	public static void customize(HashMap<String,Integer> map) {
		custom_keys.putAll(map);				
	}
	
	public static Map<String, Integer> getTemp() {
		if (temporary_keys == null) {
			temporary_keys = new HashMap<String,Integer>();
		}
		return temporary_keys;
	}

	public static void upload(String actionId, Integer keycode) {
		if (temporary_keys == null) {
			temporary_keys = new HashMap<String,Integer>() {
				{
					putAll(custom_keys);
				}
			};
		}
		temporary_keys.put(actionId, keycode);
	}

	public static void customize() {
		custom_keys.putAll(temporary_keys);
		temporary_keys = null;
	}

}
