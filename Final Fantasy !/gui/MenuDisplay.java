package gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.DataBox;

import actions.Exit;
import actions.Navigate;

public class MenuDisplay extends JPanel implements Observer {
	private AbstractButton[] buttons;
	private String lasttab;
	private static final String[] names = {"Resume","Status","Inventory","Equip","Configure","Load","Quit Game"};
	private static final Map<String,JPanel> panes = new HashMap<String,JPanel>() {
		{
			put("Status",new JPanel());
			put("Inventory", new JPanel());
			put("Equip", new JPanel());
			put("Configure", new ConfigureMenu());
			put("Load", new SaveMenu(null,false, new DataBox()));
		}
	};
	private JPanel buttonspane;
	private JPanel displaypane = new JPanel();

	public MenuDisplay(ActionListener a) {
		super(new BorderLayout());
		this.buttonspane = new JPanel();
		this.buttonspane.setLayout(new BoxLayout(this.buttonspane,BoxLayout.Y_AXIS));
		this.buttons = new AbstractButton[names.length];
		ButtonGroup group = new ButtonGroup();
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displaypane.remove(panes.get(lasttab));
				displaypane.add(panes.get(ae.getActionCommand()));
				lasttab = ae.getActionCommand();
				setBounds(0,0,500,500);
				repaint();
				System.out.println(ae.getActionCommand());
			}			
		};
		for (int i = 0; i<names.length;i++) {
			if (i==0) {
				this.buttons[i]=new JButton(names[i]);
				this.buttons[i].setAction(new Navigate(names[i]));
			} else if (i==names.length-1) {
				this.buttons[i]=new JButton(names[i]);
				this.buttons[i].setAction(new Exit(names[i]));
			} else {
				this.buttons[i] = new JRadioButton(names[i]);
				this.buttons[i].addActionListener(al);
				group.add(buttons[i]);
			}
//			this.buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			this.buttonspane.add(this.buttons[i]);
		}
		this.buttons[1].setSelected(true);
		this.add(buttonspane,BorderLayout.EAST);
		this.displaypane.add(panes.get(this.buttons[5].getActionCommand()));
		this.lasttab = buttons[5].getActionCommand();
		this.add(displaypane,BorderLayout.WEST);
		this.setSize(this.getPreferredSize());
		this.setPreferredSize(this.getSize());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
