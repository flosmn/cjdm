public class HalloUniverse {
	private volatile boolean finished = false;
	
	public static void main(String[] args) {
		System.out.println("HalloUniverse");
	}
	
	public void methodOne() {}
	public synchronized void methodTwo() {}
	private synchronized void methodThree() {}
}
