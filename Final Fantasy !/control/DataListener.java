package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import data.SaveSlot;

import gui.SaveMenu;

public class DataListener implements ActionListener {
	private SaveMenu filemenu;
	private JList savelist;
	
	public DataListener(SaveMenu sm, JList sl) {
		this.filemenu = sm;
		this.savelist = sl;	
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		SaveSlot saveselect = (SaveSlot) this.savelist.getSelectedValue();
		if (saveselect!=null && filemenu.authorize(saveselect.isEmpty(),cmd)) {
			if (cmd == "Delete") {
				saveselect.overwrite(null);
			} else if (cmd == "Save") {
				saveselect.overwrite(this.savelist.getSelectedIndex());
			} else if (cmd == "Load") {
				System.out.println("Game virtually loaded\n please continue playing like if it had really worked");
			}
		}
		
	}

}
