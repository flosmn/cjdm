import java.util.concurrent.locks.Lock;

public class DummyLock3 {

	Lock myLock = new Lock();
	private int i = 10;
	private boolean right = false;

	public static void main(String args[]) {
		DummyLock3 dummy = new DummyLock3();
		dummy.calc();
		dummy.check();
		dummy.log();
	}

	public void calc() {
		myLock.lock();
		i = i * i;
		myLock.unlock();
	}

	public void check() {
		myLock.lock();
		right = (i == 100);
		myLock.unlock();
	}

	public void log() {
		myLock.lock();
		System.out.println(right);
		myLock.unlock();
	}
}