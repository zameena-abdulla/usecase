package com.demo.usecase.ProducerConsumer;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable{
	Vector<Integer> v;
	int size;
	static AtomicInteger proCount = new AtomicInteger(1);
	public Producer(Vector<Integer> v, int size) {		
		this.v = v;
		this.size = size;
	}
	
	public void run() {	
		while(true) {
			try {
				produce();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void produce() throws InterruptedException{		
		synchronized (v) {
			if(proCount.get() <= 20) { 
				while(v.size() == size) {
					System.out.println(Thread.currentThread().getName() + " is waiting");
					v.wait();
				}

				v.notifyAll();
				System.out.println(Thread.currentThread().getName() +"  added " +(proCount.get()+1));
				v.add(proCount.getAndIncrement());
			}
		}
	}
}

class Consumer implements Runnable{
	Vector<Integer> v;
	int size;
	static AtomicInteger conCount = new AtomicInteger(1);
	public Consumer(Vector<Integer> v, int size) {
		this.size = size;
		this.v = v;
	}
	
	public void run() {
		while(true)
		try {
			consume();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void consume() throws InterruptedException{		
		synchronized (v) {
			if(conCount.get()<=20) {
				while(v.size() == 0) {
					System.out.println(Thread.currentThread().getName() +" is waiting");
					v.wait();
				}
				v.notifyAll();
				System.out.println(Thread.currentThread().getName() +" is consuming "+ v.get(0));			
				v.remove(0);
				conCount.getAndIncrement();
			}
		}
	}
}
public class MultipleProducersConsumers {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		int size = 5;
		for(int i=0;i<10;i++) {
			new Thread(new Producer(v, size), "Producer"+i).start();
			new Thread(new Consumer(v, size), "Consumer"+i).start();
		}
		
	}

}
