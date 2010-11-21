import java.util.concurrent.locks.Lock;

public class DummyLock3 {

	Lock lock = new Lock();
	private int i = 10;
	private boolean right = false;

	public static void main(String args[]) {
		DummyLock3 dummy = new DummyLock3();
		dummy.calc();
		dummy.check();
		dummy.log();
	}

	public void calc() {
		lock.lock();
		i = i * i;
		lock.unlock();
	}

	public void check() {
		lock.lock();
		right = (i == 100);
		lock.unlock();
	}

	public void log() {
		lock.lock();
		System.out.println(right);
		lock.unlock();
	}
}