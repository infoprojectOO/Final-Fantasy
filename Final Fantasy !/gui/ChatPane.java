package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import chat.ChatBox;


public class ChatPane extends JPanel implements Observer {
	private RootLayer parent;
	private Point location = new Point(20,20);
	private ChatBox chatbox;
	private boolean visible;
	private MouseListener labelistener;

	public ChatPane(RootLayer layer, ChatBox chat, MouseListener lablis) {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.visible = false;
		this.parent = layer;
		this.chatbox = chat;
		chat.addObserver(this);
		this.labelistener = lablis;
	}

	@Override
	public void update() {
		this.setLocation(this.location);
		this.removeAll();
		if (!visible) {
			this.parent.addLayer(this, parent.MESSAGE);
			this.visible = true;
		}
		String[] message = this.chatbox.extract();
		for (String text : message) {
			JLabel label = new JLabel(text);
			label.addMouseListener(labelistener);
			this.add(label);
			this.add(new JSeparator());
		}
		this.setSize(this.getPreferredSize());
		this.parent.validate();
		this.repaint();
		if (message.length==0) {
			this.chatbox.terminate();
			this.visible = false;
			this.parent.removeLayer(parent.MESSAGE);
		}
		
	}
	
	
	


}
