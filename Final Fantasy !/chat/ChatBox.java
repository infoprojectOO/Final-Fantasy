package chat;

import java.util.List;

import character.People;
import gui.Observer;
import gui.Enquirer;

public class ChatBox implements Enquirer {
	private Observer observer;
	private ChatTree chat;
	private int counter = 0;
	private String[] flow;
	private People interlocutor;

	public void receive(String answerkey) {
		System.out.println(counter);
		if (counter>flow.length) {
			this.chat.nextNode(answerkey);
			this.flow = this.chat.getNextFlow();
			this.counter = 0;
		} this.observer.update();
	}

	public String[] extract() {
		System.out.println(counter);
		String[] res;
		if (counter<flow.length) {
			res = new String[] {flow[counter]};
		} else {
			res = this.chat.getNextChoice();
		}
		counter += 1;
		return res;
	}
	
	public void terminate() {
		this.counter = 0;
		this.interlocutor.talk(false);
		this.chat = null;
		this.flow = null;
	}
	
	public void upload(ChatTree chat, People person) {
		this.chat = chat;
		this.flow = chat.getNextFlow();
		this.interlocutor = person;
		this.observer.update();
	}

	@Override
	public void addObserver(Observer o) {
		this.observer = o;
		
	}
	
	
	
	

}
