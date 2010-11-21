import java.util.concurrent.locks.Lock;

public class NestedLocks {
	Lock lock1 = new Lock();
	Lock lock2 = new Lock();
	Lock lock3 = new Lock();
	
	public void lock() {
		lock1.lock();
		lock2.lock();
		// do something
		lock2.unlock();
		lock1.unlock();
	}

	public void lock() {
		lock1.lock();
		lock2.lock();
		lock3.lock();
		// do something
		lock3.unlock();
		lock2.unlock();
		lock1.unlock();
	}
	
	public void lock() {
		lock1.lock();
		lock2.lock();
		lock1.unlock();
		lock3.lock();
		// do something
		lock3.unlock();
		lock2.unlock();
	}
}
