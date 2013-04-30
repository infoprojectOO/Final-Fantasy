package modeltest;

import static org.junit.Assert.*;
import gui.Observer;

import org.junit.Before;
import org.junit.Test;

import control.ChatBox;

import character.ChatTree;

public class ChatBoxTest {
	private ChatBox chatbox;
	private ChatTree chat;

	@Before
	public void setUp() throws Exception {
		this.chatbox = new ChatBox();
		this.chatbox.addObserver(new Observer(){
			@Override
			public void update() {}
		});
		this.chat = new ChatTree();
		this.chatbox.upload(chat);
	}

	@Test
	public void testExtractReceive() {
		assertEquals(this.chatbox.extract()[0],chat.getNextFlow().get(0));
		this.chatbox.receive(chat.getNextFlow().get(0));
		assertTrue(this.chatbox.extract()[0].equals(chat.getNextFlow().get(1)));
		this.chatbox.receive(chat.getNextFlow().get(1));
		assertTrue(this.chatbox.extract()[0].equals(chat.getNextChoice()[0]));
		this.chatbox.receive(chat.getNextChoice()[0]);
		assertEquals(this.chatbox.extract()[0],chat.getNextFlow().get(0));
		assertTrue(this.chatbox.extract()[0].equals(chat.getNextFlow().get(1)));
		assertEquals(this.chatbox.extract(),null);
		this.chatbox.terminate();

		
	}
}
