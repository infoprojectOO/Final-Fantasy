package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gui.Enquirer;
import gui.Observer;

public class BoardBox implements Enquirer {
	private PlayBoard board;
	private boolean warping;
	private List<Observer> observers = new ArrayList<Observer>();
	
	public BoardBox() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addObserver(Observer o) {
		this.observers.add(o);		
	}

	public boolean isWarping() {
		return warping;
	}

	public void setWarping(boolean warping) {
		this.warping = warping;
	}

	public PlayBoard getBoard() {
		return board;
	}

	public void upload(PlayBoard board) {
		this.board = board;
		this.warping = true;
		for (Observer o : observers) {
			o.update();
		}
	}

}
