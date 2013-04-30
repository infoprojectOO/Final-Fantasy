package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextNode {
	private List<String> chatflow;
	private List<TextNode> ways;
	private String answerkey;

	public TextNode(String message) {
		this.answerkey = message;
		this.chatflow = new ArrayList<String>();
		this.ways = new ArrayList<TextNode>();
		this.chatflow.add("Hello, my name is Caetun");
		this.chatflow.add("And who are you ?");
	}
	
	public String getAnswerKey() {
		return this.answerkey;
	}

	public void connect(TextNode node) {
		this.ways.add(node);
	}
	
	public TextNode getNode(String answerkey) {
		for (TextNode node : ways) {
			if (node.getAnswerKey() == answerkey) {
				return node;
			}
		}
		return null;
	}

	public List<String> getChatFlow() {
		return this.chatflow;
	}

	public String[] getWays() {
		String[] choice = new String[this.ways.size()];
		for (int i=0;i<this.ways.size();i++) {
			choice[i] = this.ways.get(i).getAnswerKey();
		}
		return choice;
	}
	

}
