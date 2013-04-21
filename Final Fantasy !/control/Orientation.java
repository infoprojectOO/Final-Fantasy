package control;

public enum Orientation {
	UP ("up",'Y',new Integer[] {0,1}),
	DOWN ("down",'Y', new Integer[] {0,-1}),
	LEFT ("left",'X', new Integer[] {-1,0}),
	RIGHT ("right",'X', new Integer[] {1,0}),
	X ('X'),
	Y ('Y');
	
	private String arrow;
	private char axis;
	private Integer[] shifting;

	private Orientation(String name, char axis, Integer[] coord) {
		this.axis = axis;
		this.arrow = name;
		this.shifting = coord;
	}
	
	private Orientation(char axis) {
		this.axis = axis;
	}
	
	public char getAxis(){
		return this.axis;
	}

	public Integer[] getShifting() {
		return shifting;
	}

}
