package usecase.CustomCyclicBarrier;import javax.crypto.AEADBadTagException;

class CustomCyclicBarrier{
	private int initialCount;
	private int awatingCount;	
	private Runnable cyclicBarrieEvent;
	
	public CustomCyclicBarrier(int count, Runnable event) {
		this.initialCount = count;
		this.awatingCount = count;
		this.cyclicBarrieEvent = event;
	}
	
	synchronized void await() throws InterruptedException{
		awatingCount --;
		if(awatingCount >0) {
			this.wait();
		}
		else {
			awatingCount = initialCount;
			notifyAll();
			this.cyclicBarrieEvent.run();
		}
	}	
}

class MyThread implements Runnable{
	CustomCyclicBarrier b1;
	CustomCyclicBarrier b2;
	public MyThread(CustomCyclicBarrier b1, CustomCyclicBarrier b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " waiting in barrier 1");
			b1.await();
			Thread.sleep(100);
			System.out.println(Thread.currentThread().getName() + " waiting in barrier 2");
			b2.await();
			Thread.sleep(100);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
 	
	
}
public class CustomCyclicBarrierDemo 
{
    public static void main( String[] args )
    {
        Runnable r1 = ()-> System.out.println("Barrier 1 is done");
        Runnable r2 = ()-> System.out.println("Barrier 2 is done");
        CustomCyclicBarrier b1 = new CustomCyclicBarrier(2, r1);
        CustomCyclicBarrier b2 = new CustomCyclicBarrier(2, r2);
        
        Thread t1 = new Thread(new MyThread(b1, b2), "t1");
        Thread t2 = new Thread(new MyThread(b1, b2), "t2");
        t1.start();
        t2.start();
        
    }
}
