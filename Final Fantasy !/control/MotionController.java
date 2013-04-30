package control;

import gui.Observer;

import world.PlayBoard;

import character.IMotionCharacter;

public class MotionController implements Runnable {
	private Thread clock;
	private PlayBoard motionboard;
	private Observer o;
	
	public MotionController(PlayBoard motionboard) {
		this.clock = new Thread();
		this.motionboard = motionboard;
		this.clock.start();
	}

	@Override
	public void run() {
		while(true) {
			for (IMotionCharacter character : this.motionboard.getMotionCharacters()) {
				character.requestMove();				
			}
			System.out.println("moving");
			this.o.update();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
