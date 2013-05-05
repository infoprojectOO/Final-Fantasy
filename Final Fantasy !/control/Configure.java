package control;

import gui.ConfigureMenu;
import gui.DoubleTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import actions.Navigate;

import settings.KeyMap;

public class Configure implements KeyListener, ActionListener {

	public Configure() {}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "reset") {
				ConfigureMenu.reset(KeyMap.default_keys);
		} else if (cmd == "cancel") {
			ConfigureMenu.reset(KeyMap.custom_keys);
			new Navigate().actionPerformed(evt);
		} else if (!KeyMap.getTemp().containsValue(null)) {
			try { 
				KeyMap.customize();
				new Navigate().actionPerformed(evt);
			} catch (ClassCastException c) {System.err.println("Vacant Key Bindings");}
		} else {
			System.out.println("Vacant Key Bindings");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		e.consume();		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object source = e.getSource();
		if (source instanceof DoubleTextField) {
			int keycode = e.getKeyCode();
			if (!KeyMap.getTemp().containsValue(keycode)) {
				((DoubleTextField) source).setValue(keycode);
				KeyMap.upload(((DoubleTextField) source).getActionID(),keycode);
			} else if (((DoubleTextField) source).getValue()!=null && ((DoubleTextField) source).getValue().equals(keycode)) {
				KeyMap.upload(((DoubleTextField) source).getActionID(),null);
				((DoubleTextField) source).setText("< Vacant >");
				System.out.println("deleted");
			} else if (((DoubleTextField) source).getValue()==null) {
				System.out.println("null value !");
			} 
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();		
	}

}
