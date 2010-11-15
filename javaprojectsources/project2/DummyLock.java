import java.util.concurrent.locks.Lock;

public class DummyLock {

	Lock myLock = new Lock();
	private int i = 5;
	
	public static void main(String args[]){
		DummyLock dummy = new DummyLock();
		dummy.calc();
		dummy.log();
	}
	
	public void calc() {
		myLock.lock();
		i = i * i;
		myLock.unlock();
	}
	
	public void log() {
		myLock.lock();
		System.out.println("value: "+i);
		myLock.unlock();
	}
}
