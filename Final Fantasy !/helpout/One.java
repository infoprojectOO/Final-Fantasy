package helpout;

public class One {
	private Two two;

	public One() {
		this.two = new Two();
	}

	public static void main(String[] args) {
		One one = new One();
		one.two.pui = 0;
		int i = one.two.getPri();
		i = 3;
		one.two.pul.add("nice");
		one.two.getPrl().add("nice2");
		System.out.println(one.two.pui);
		System.out.println(one.two.getPri());
		System.out.println(one.two.pul);
		System.out.println(one.two.getPrl());
		
		
	}
}
