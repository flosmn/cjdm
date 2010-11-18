public class ConcurrencyObject {
	private volatile boolean finished = false;
	
	private Executor executor;
	private ConcurrentMap concurrentMap;
	private AtomicInteger atomicInteger;
	private BlockingQueue blockingQueue;
	private ConcurrentLinkedQueue concurrentLinkedQueue;
	private CopyOnWriteArrayList copyOnWriteArrayList;
	private DeleyQueue deleyQueue;
	private ExecutorService executorService;
	private Future future;
	private Lock lock;
	
	public static void main(String[] args) {
		System.out.println("HalloUniverse");
	}
	
	public void methodOne() {}
	public synchronized void methodTwo() {}
	private synchronized void methodThree() {}
}
