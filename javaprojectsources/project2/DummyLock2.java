import java.util.concurrent.locks.Lock;

public class DummyLock2 {

	Lock myLock = new Lock();
	private int i = 10;
	private boolean correct = false;

	public static void main(String args[]) {
		DummyLock2 dummy = new DummyLock2();
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
		correct = (i == 100);
		myLock.unlock();
	}

	public void log() {
		myLock.lock();
		System.out.println(correct);
		myLock.unlock();
	}
}