package control;

import java.util.List;

import character.ChatTree;
import gui.Observer;
import gui.Subject;

public class ChatBox implements Subject {
	private Observer observer;
	private ChatTree chat;
	private int counter = 0;
	private List<String> flow;

	public void receive(String answerkey) {
		System.out.println(counter);
		if (counter>flow.size()) {
			this.chat.nextNode(answerkey);
			this.flow = this.chat.getNextFlow();
			this.counter = 0;
		} this.observer.update();
	}

	public String[] extract() {
		System.out.println(counter);
		String[] res;
		if (counter<flow.size()) {
			res = new String[] {flow.get(counter)};
		} else {
			res = this.chat.getNextChoice();
		}
		counter += 1;
		return res;
	}
	
	public void terminate() {
		this.counter = 0;
		this.chat.rewind();
		this.chat = null;
		this.flow = null;
	}
	
	public void upload(ChatTree chat) {
		this.chat = chat;
		this.flow = chat.getNextFlow();
		this.observer.update();
	}

	@Override
	public void addObserver(Observer o) {
		this.observer = o;
		
	}
	
	
	
	

}
