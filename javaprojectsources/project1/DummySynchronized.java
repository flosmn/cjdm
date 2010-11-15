public class DummySynchronized {

	private int i = 5;
	
	public static void main(String args[]){
		DummySynchronized dummy = new DummySynchronized();
		dummy.calc();
		dummy.log();
	}
	
	public void calc() {
		int p = 5;
		synchronized(this){
			i = i * i;
		}
	}
	
	public void log() {
		synchronized(this) {
			System.out.println("value: "+i);
		}
	}
}
