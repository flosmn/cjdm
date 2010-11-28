import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DummyLock extends Executor{

	Lock lock = new Lock();
	private int i = 5;
	private final Lock locked = new ReentrantLock();
	private ConcurrentMap<Object> map = new ConcurrentMap<Object>();
	private ConcurrentMap map2= new ConcurrentMap();
	
	public static void main(String args[]){
		final Lock lock = new ReentrantLock();
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
