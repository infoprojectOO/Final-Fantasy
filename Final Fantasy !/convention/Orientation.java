package convention;


public enum Orientation {
	UP ("up",Axis.Y,new Integer[] {0,1}),
	DOWN ("down",Axis.Y, new Integer[] {0,-1}),
	LEFT ("left",Axis.X, new Integer[] {-1,0}),
	RIGHT ("right",Axis.X, new Integer[] {1,0});
	
	private String arrow;
	private Axis axis;
	private Integer[] shifting;

	private Orientation(String name, Axis axis, Integer[] coord) {
		this.axis = axis;
		this.arrow = name;
		this.shifting = coord;
	}
	
	public Axis getAxis(){
		return this.axis;
	}

	public Integer[] getShifting() {
		return shifting;
	}

}
