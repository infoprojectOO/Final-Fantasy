package gui;

import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class ConfigFormat extends AbstractFormatter {

	@Override
	public Object stringToValue(String arg0) throws ParseException {
		return null;
	}

	@Override
	public String valueToString(Object i) throws ParseException {
		String res = null;
		try {res = KeyEvent.getKeyText((Integer) i);} 
		catch (NullPointerException e) {}
		return res;
	}

}
