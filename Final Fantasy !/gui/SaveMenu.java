package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import actions.Navigate;

public class SaveMenu extends JPanel implements ActionListener {
	private JPanel slotpane;
	private JPanel buttonspane;
	private JOptionPane confirm;
	private final String[] messages = {"Are you sure you want to delete this save file ?\n" 
			+ "Once deleted, it will not be possible to recover your saveslot","This slot already contains a file,\n"
			+ "Do you want to overwrite it ?"};
	private SaveSlot[] slots = new SaveSlot[10];
	private JList<SaveSlot> savelist;
	private JButton saveload,delete,cancel;
	
	public SaveMenu(boolean output) {
		super(new BorderLayout());
		for (int i=0;i<slots.length;i++) {
			slots[i] = new SaveSlot();
		}
		this.buttonspane = new JPanel();
		this.confirm = new JOptionPane(messages[0], JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		this.slotpane = new JPanel();
		this.saveload = new JButton("Save");
		if (!output) {
			this.saveload.setText("Load");
		}
		this.delete = new JButton("Delete");
		this.cancel = new JButton("Cancel");
		this.cancel.setAction(new Navigate(cancel.getText()));
		JButton[] btts = {saveload,delete,cancel};
		for (JButton b : btts) {
			b.addActionListener(this);
			this.buttonspane.add(b);			
		}
		this.savelist = new JList<SaveSlot>(slots);
		this.savelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.slotpane.add(savelist);
		this.add(slotpane,BorderLayout.NORTH);
		this.add(buttonspane, BorderLayout.SOUTH);
		this.setSize(this.getPreferredSize());
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		SaveSlot saveselect = savelist.getSelectedValue();
		if (saveselect!=null) {
			if (!saveselect.isEmpty()) {
				if (cmd == "Delete") {
					confirm.setMessage(messages[0]);
					confirm.createDialog("Confirm Deletion").setVisible(true);
					Integer choice = ((Integer) confirm.getValue()).intValue();
					if (choice == JOptionPane.YES_OPTION) {
						System.out.println(true);
						saveselect.overwrite();
					} else if (choice == JOptionPane.NO_OPTION) {
						System.out.println(false);
					}
				} else if (cmd == "Save") {
					if (!saveselect.isEmpty()) {
						confirm.setMessage(messages[1]);
						confirm.createDialog("Confirm Overwriting").setVisible(true);
						Integer choice = ((Integer) confirm.getValue()).intValue();
						if (choice == JOptionPane.YES_OPTION) {
							System.out.println(true);
						}
					}
				}
			}
		}
		
	}

}
