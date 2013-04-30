package character;

import java.util.ArrayList;
import java.util.List;


public class ChatTree {
	private TextNode root;
	private TextNode currentnode;

	public ChatTree() {
		this.root = new TextNode(null);
		this.currentnode = root;
		this.root.connect(new TextNode("I'm Cloud what's up ?"));
		this.root.connect(new TextNode("My name shall remain secret..."));
	}
	
	public List<String> getNextFlow() {
		return this.currentnode.getChatFlow();	
	}
	
	public String[] getNextChoice() {
		return this.currentnode.getWays();	
	}
	
	public void nextNode(String answerkey) {
		this.currentnode = this.currentnode.getNode(answerkey);
	}

	public void rewind() {
		this.currentnode = root;		
	}
	
	

}
