import java.util.concurrent.locks.Lock;

public class DummyLock2 {

	Lock lock = new Lock();
	private int i = 10;
	private boolean correct = false;

	public static void main(String args[]) {
		DummyLock2 dummy = new DummyLock2();
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
		correct = (i == 100);
		lock.unlock();
	}

	public void log() {
		lock.lock();
		System.out.println(correct);
		lock.unlock();
	}
}