package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.Launcher;

public class MainMenu extends JPanel {
	private JButton[] buttons;
	private Launcher controller;
	private static final String[] names = {"New Game","Continue","Configure","Exit"};
	
	public MainMenu(Launcher launcher) {
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.buttons = new JButton[4];
		this.controller = launcher;
		for (int i = 0; i<=3;i++) {
			this.buttons[i] = new JButton(names[i]);
			this.buttons[i].addActionListener(this.controller);
			this.buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			this.add(this.buttons[i]);
		}
	}

	public Object getButton(int i) {
		return this.buttons[i];
	}
}
