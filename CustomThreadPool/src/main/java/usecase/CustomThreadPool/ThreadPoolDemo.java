package usecase.CustomThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


class CustomThreadPool{
	
	private class ThreadPoolThread extends Thread{
		AtomicBoolean execute;
		ConcurrentLinkedQueue<Runnable> runnables;
		public ThreadPoolThread(AtomicBoolean execute, ConcurrentLinkedQueue<Runnable> runnables) {
			this.execute = execute;
			this.runnables = runnables;
		}
		
		public void run() {
			try {
				while(execute.get() || !runnables.isEmpty()) {
					Runnable runnable ;
					while((runnable= runnables.poll()) != null) {
						runnable.run();
					}
					Thread.sleep(100);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ThreadpoolException extends RuntimeException {
        public ThreadpoolException(Throwable cause) {
            super(cause);
        }
    }
	
	private static AtomicInteger poolCount = new AtomicInteger(0);
	private ConcurrentLinkedQueue<Runnable> runnables;
	private AtomicBoolean execute;
	private List<ThreadPoolThread> threads;
	
	private CustomThreadPool(int threadNo) {
		poolCount.incrementAndGet();
		this.runnables = new ConcurrentLinkedQueue<Runnable>();
		this.execute = new AtomicBoolean(true);
		this.threads = new ArrayList<ThreadPoolThread>();
		for(int i=0;i<threadNo;i++) {
			ThreadPoolThread t = new ThreadPoolThread(execute, runnables);
			t.start();
			threads.add(t);
		}		
	}
	
	public static CustomThreadPool getInstance() {
        return getInstance(Runtime.getRuntime().availableProcessors());
    }

	public static CustomThreadPool getInstance(int threadNo) {
		return new CustomThreadPool(threadNo);
	}
	
	public void execute(Runnable runnable) {
		if(this.execute.get()) {
			this.runnables.add(runnable);
		}
		else {
			throw new IllegalStateException("Threadpool terminating, unable to execute runnable");
		}
	}
	public void awaitTermination() {
		if(this.execute.get()) {
			throw new IllegalStateException();
		}
		while(true) {
			boolean flag = true;
			for(Thread thread:threads) {
				if(thread.isAlive()) {
					flag = false;
					break;
				}
			}
		}
	}
	
	public void terminate() {
		runnables.clear();
		stop();
	}
	
	public void stop() {
		execute.set(false);
	}
}
public class ThreadPoolDemo 
{
    public static void main( String[] args )
    {
       
    }
}
