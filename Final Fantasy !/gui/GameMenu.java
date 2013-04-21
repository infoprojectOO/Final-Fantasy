package gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
	private JButton[] buttons;
	private static final String[] names = {"Resume","Load","Configure","Items","Status"};
	private JPanel buttonspane;
	private JPanel displaypane;

	public GameMenu(ActionListener al) {
		super(new BorderLayout());
		this.buttonspane = new JPanel();
		this.buttonspane.setLayout(new BoxLayout(this.buttonspane,BoxLayout.Y_AXIS));
		this.buttons = new JButton[names.length];
		for (int i = 0; i<names.length;i++) {
			this.buttons[i] = new JButton(names[i]);
			this.buttons[i].addActionListener(al);
			this.buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			this.buttonspane.add(this.buttons[i]);
		}
		this.add(buttonspane,BorderLayout.EAST);
		this.setSize(this.getPreferredSize());
	}

}
