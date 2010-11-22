public class DummySynchronized2 {

	private int i = 10;
	private boolean right = false;
	
	public static void main(String args[]){
		DummySynchronized2 dummy = new DummySynchronized2();
		dummy.calc();
		dummy.check();
		dummy.log();
	}
	
	public void calc() {
		synchronized(this){
			i = i * i;
		}
	}
	
	public void check() {
		synchronized(this){
			right = (i == 100);
		}
	}
	
	public void log() {
		synchronized(this) {
			System.out.println(right);
		}
	}
}