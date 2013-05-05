package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.Observer;

import world.Box;
import world.PlayBoard;

import character.IMotionCharacter;

public class MotionController implements Runnable, Observer {
	private Thread clock;
	private PlayBoard board;
	private Observer observer;
	private List<IMotionCharacter> chars;
	
	public MotionController(PlayBoard motionboard) {
		this.clock = new Thread(this);
		this.board = motionboard;
		Box.board.addObserver(this);
	}

	@Override
	public void run() {
		while(true) {
			chars = this.board.getMotionCharacters();
			IMotionCharacter[] charsforit = chars.toArray(new IMotionCharacter[chars.size()]);
			synchronized(charsforit) {
				for (IMotionCharacter character : charsforit) {
					character.requestMove();
				}					
			}
			this.observer.update();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addObserver(Observer o) {
		this.observer = o;
		this.clock.start();
	}

//	public void setBoard(PlayBoard boardmodel) {
//		this.board = boardmodel;		
//	}

	@Override
	public void update() {
		System.out.println("motion");
		this.board = Box.board.getBoard();
	}

}
