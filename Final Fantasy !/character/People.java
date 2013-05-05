package character;

import java.awt.Dimension;
import java.awt.Image;

import chat.ChatBox;
import chat.ChatTree;

import add_on.GraphImage;

import convention.Access;
import convention.Axis;
import convention.Measurement;
import world.IMapComponent;

public abstract class People implements IMapComponent {
	private Image look;
	private Dimension size;
	private ChatTree chat;
	private ChatBox chatbox;
	protected boolean talking = false;

	public People(Measurement mes,String name, ChatBox chatbox) {
		this.size = mes.getSize();
		this.look = GraphImage.getImage(name, this);
		this.chat = new ChatTree();
		this.chatbox = chatbox;
	}
	
	public void talk(boolean state) {
		this.talking = state;
		if (talking) {
			this.chatbox.upload(chat,this);
		} else {
			this.chat.rewind();
		}
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
		talk(true);
	}

	@Override
	public boolean allowsAccess(Access ackey) {
		return false;
	}
}
