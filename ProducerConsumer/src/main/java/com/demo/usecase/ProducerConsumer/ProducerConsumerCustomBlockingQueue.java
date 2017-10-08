package com.demo.usecase.ProducerConsumer;

import java.util.Vector;

class CustomBlockingQueue{
	Vector<Integer> v;
	int maxSize;
	
	CustomBlockingQueue(int maxSize){
		this.maxSize = maxSize;
		v = new Vector<>();
	}
	
	void put(int i) throws InterruptedException{
		synchronized (v) {
			while(v.size() == maxSize) {
				v.wait();
			}
		}
		synchronized (v) {
			System.out.println("Producing value " +i);
			v.add(i);
			v.notify();	
		}
	}
	
	void get() throws InterruptedException{
		synchronized (v) {
			while(v.size() == 0) {
				v.wait();
			}
		}
		synchronized(v) {
			System.out.println("Consuming " + v.get(0));
			v.remove(0);
			v.notify();
		}
	}
	
}

class Producer4 implements Runnable{
	CustomBlockingQueue q;
	int size;
	
	public Producer4(CustomBlockingQueue q) {		
	    this.q = q;
	}
	
	public void run() {
		for(int i=1;i<20;i++) {
			try {
				q.put(i);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer4 implements Runnable{
	CustomBlockingQueue q;
	public Consumer4(CustomBlockingQueue q) {
		this.q = q;
	}
	
	public void run(){
		try {
			for(int i=1;i<20;i++) {
				q.get();
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public class ProducerConsumerCustomBlockingQueue {
	public static void main(String[] args) {
		int size = 5;
		CustomBlockingQueue q = new CustomBlockingQueue(size);
		Thread prod = new Thread(new Producer4(q));
		Thread cons = new Thread(new Consumer4(q));
		prod.start();
		cons.start();
	}

}


