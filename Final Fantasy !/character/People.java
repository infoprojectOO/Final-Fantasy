package character;

import java.awt.Dimension;
import java.awt.Image;

import add_on.GraphImage;

import control.ChatBox;
import convention.Axis;
import convention.Measurement;
import world.IMapComponent;

public abstract class People implements IMapComponent {
	private Image look;
	private Dimension size;
	private ChatTree chat;
	private ChatBox chatbox;

	public People(Measurement mes,String name, ChatBox chatbox) {
		this.size = mes.getSize();
		this.look = GraphImage.getImage(name, this);
		this.chat = new ChatTree();
		this.chatbox = chatbox;
	}
	
	private void talk() {
		this.chatbox.upload(chat);
	}
	
	public void setLook(String name) {
		this.look = GraphImage.getImage(name, this);		
	}
	
	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public Integer getBreadth(Axis axis) {
		if (axis==Axis.X) {
			return (int) this.size.getWidth();
		} else {
			return (int) this.size.getHeight();
		}
	}
	
	@Override
	public void awaken() {
		talk();
	}

}
