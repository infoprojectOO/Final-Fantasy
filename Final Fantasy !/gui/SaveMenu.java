package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import control.DataListener;

import data.DataBox;
import data.SaveSlot;

import actions.Navigate;

public class SaveMenu extends JPanel implements Observer {
	private RootLayer parent;
	private JPanel slotpane;
	private JPanel buttonspane;
	private JOptionPane confirm;
	private final String[] messages = {"Are you sure you want to delete this save file ?\n" 
			+ "Once deleted, it will not be possible to recover your saveslot","This slot already contains a file,\n"
			+ "Do you want to overwrite it ?", "Any progress that has been done will be lost when loading a saved game.\n"
			+ "Are you sure you want to load a previous game ?"};
	private final Map<String, String> warnings = new HashMap<String,String>() {
		{
			put("Delete", messages[0]);
			put("Save", messages[1]);
			put("Load", messages[2]);
		}
	};
	private JList<SaveSlot> savelist;
	private DataBox databox;
	private JButton saveload,delete,cancel;
	private final Map<String, String> translate = new HashMap<String,String>() {
		{
			put("Delete", "Deletion");
			put("Save", "Overwriting");
			put("Load", "Loading");
		}
	};
	
	public SaveMenu(RootLayer layer, boolean output, DataBox db) {
		super(new BorderLayout());
//		for (int i=0;i<slots.length;i++) {
//			slots[i] = new SaveSlot();
//		}
		this.parent = layer;
		this.databox = db;
		this.databox.addObserver(this);
		this.buttonspane = new JPanel();
		this.confirm = new JOptionPane(null, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		this.slotpane = new JPanel();
		this.savelist = new JList<SaveSlot>(this.databox.getSlots());
		this.savelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.saveload = new JButton("Save");
		if (!output) {
			this.saveload.setText("Load");
		}
		DataListener controller = new DataListener(this,this.savelist);
		this.delete = new JButton("Delete");
		this.cancel = new JButton("Cancel");
		this.cancel.setAction(new Navigate(cancel.getText()));
		JButton[] btts = {saveload,delete,cancel};
		for (JButton b : btts) {
			b.addActionListener(controller);
			this.buttonspane.add(b);			
		}
		this.slotpane.add(savelist);
		this.add(slotpane,BorderLayout.NORTH);
		this.add(buttonspane, BorderLayout.SOUTH);
		this.setSize(this.getPreferredSize());
	}

//	@Override
//	public void actionPerformed(ActionEvent evt) {
//		String cmd = evt.getActionCommand();
//		SaveSlot saveselect = savelist.getSelectedValue();
//		if (saveselect!=null) {
//			if (!saveselect.isEmpty()) {
//				if (cmd == "Delete") {
//					confirm.setMessage(messages[0]);
//					confirm.createDialog("Confirm Deletion").setVisible(true);
//					Integer choice = ((Integer) confirm.getValue()).intValue();
//					if (choice == JOptionPane.YES_OPTION) {
//						System.out.println(true);
//						saveselect.overwrite(null);
//					} else if (choice == JOptionPane.NO_OPTION) {
//						System.out.println(false);
//					}
//				} else if (cmd == "Save") {
//					if (!saveselect.isEmpty()) {
//						confirm.setMessage(messages[1]);
//						confirm.createDialog("Confirm Overwriting").setVisible(true);
//						Integer choice = ((Integer) confirm.getValue()).intValue();
//						if (choice == JOptionPane.YES_OPTION) {
//							System.out.println(true);
//						}
//					}
//				}
//			}
//		}
//		
//	}
	
	public boolean authorize(boolean empty, String cmd) {
		boolean authorization = true;
		if (!empty) {
			this.confirm.setMessage(this.warnings.get(cmd));
			confirm.createDialog("Confirm "+translate.get(cmd)).setVisible(true);
			Integer choice = ((Integer) confirm.getValue()).intValue();
			if (choice == JOptionPane.NO_OPTION) {
				authorization = false;
			}
		} else if (cmd == "Load") {
			authorization = false;
		} return authorization;
	}

	@Override
	public void update() {
		this.parent.addLayer(this, parent.MENU);
	}

}
