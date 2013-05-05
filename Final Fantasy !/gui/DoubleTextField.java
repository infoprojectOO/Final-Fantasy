package gui;

import java.text.Format;

import javax.swing.JFormattedTextField;

public class DoubleTextField extends JFormattedTextField {
	private final String actionid;

	public DoubleTextField(AbstractFormatter formatter,String name) {
		super(formatter);
		this.actionid = name;		
	}

	public String getActionID() {
		return actionid;
	}


}
