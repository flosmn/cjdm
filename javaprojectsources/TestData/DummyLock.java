import java.util.concurrent.locks.Lock;

public class DummyLock extends Executor{

	Lock lock = new Lock();
	private int i = 5;
	
	public static void main(String args[]){
		DummyLock dummy = new DummyLock();
		dummy.calc();
		dummy.log();
	}
	
	public void calc() {
		lock.lock();
		i = i * i;
		lock.unlock();
	}
	
	public void log() {
		lock.lock();
		System.out.println("value: "+i);
		lock.unlock();
	}
}
