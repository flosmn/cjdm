public class ObjectCalls{
	
	Object o = new Object();
	
	public void fooCall(){
		o.sleep();
		o.wait();
		o.notify();
	}
	
	public void barCall(){
		o.notifyAll();
		o.yield();
		o.join();
	}
	
	
}
