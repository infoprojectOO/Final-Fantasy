package convention;

public enum Axis {
	X,Y;
	
	public Axis reverse() {
		if (this.equals(X)) {
			return (Y);
		} else {
			return (X);
		}
	}
}
