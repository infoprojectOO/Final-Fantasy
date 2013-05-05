package control;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

import chat.ChatBox;

public class ChatListener implements MouseListener {
	private ChatBox chatbox;
	
	public ChatListener(ChatBox chat) {
		this.chatbox = chat;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		this.chatbox.receive(((JLabel) evt.getComponent()).getText());
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	@Override
	public void mouseExited(MouseEvent evt) {
		evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

}
