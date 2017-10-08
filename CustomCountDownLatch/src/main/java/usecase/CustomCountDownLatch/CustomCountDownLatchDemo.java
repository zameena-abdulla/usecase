package usecase.CustomCountDownLatch;

class CustomCountDownLatch{
	private int count;
	
	public CustomCountDownLatch(int count) {
		this.count = count;
	}
	
	synchronized void countDown() {
		count--;
		if(count == 0) {
			notifyAll();
		}
	}
	
	synchronized void await() throws InterruptedException{
		while(count >0) {
			System.out.println("Waiting.");
			wait();
		}
	}
}

class Decrementer implements Runnable{
	CustomCountDownLatch latch;
	public Decrementer(CustomCountDownLatch latch) {
		this.latch =  latch;
	}
	
	public void run() {
		try {
			Thread.sleep(100);
			System.out.println("Decrementer calling countDown");
			this.latch.countDown();
			Thread.sleep(100);
			System.out.println("Decrementer calling countDown");
			this.latch.countDown();
			Thread.sleep(100);
			System.out.println("Decrementer calling countDown");
			this.latch.countDown();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
}

class Waiter implements Runnable{
	CustomCountDownLatch latch;
	public Waiter(CustomCountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		try {			
			latch.await();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Waiter released");
	}
	
}
public class CustomCountDownLatchDemo 
{
    public static void main( String[] args )
    {
        CustomCountDownLatch latch = new CustomCountDownLatch(3);
        Thread decrementer = new Thread(new Decrementer(latch));
        Thread waiter = new Thread(new Waiter(latch));
        
        decrementer.start();
        waiter.start();
    }
}
