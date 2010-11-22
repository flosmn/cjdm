public class ConcurrencyObject implements Callable, Delayed, ThreadFactory{
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
	private ThreadPoolExecutor threadPoolExecutor;
	private ThreadPoolExecutor threadPoolExecutor;
	private CountDownLatch countDownLatch;
	private CyclicBarrier cyclicBarrier;
	private Exchanger exchanger;
	private Semaphore semaphore;
	
	public static void main(String[] args) {
		System.out.println("HalloUniverse");
		methodZero();
		methodFour();
	}
	
	public void methodZero() {
		methodOne();
		methodTwo();
		methodTree();
		methodFour();
	}
	
	public void methodOne() {
	}
	public synchronized void methodTwo() {}
	private synchronized void methodThree() {}
	private synchronized int methodFour() {}
}
