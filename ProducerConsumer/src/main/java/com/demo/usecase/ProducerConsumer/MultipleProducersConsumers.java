package com.demo.usecase.ProducerConsumer;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable{
	ArrayList<Integer> v;
	int size;
	static volatile int proCount = 1;
	public Producer(ArrayList<Integer> v, int size) {		
		this.v = v;
		this.size = size;
	}
	
	public void run() {	
		while(proCount<20) {
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
			while(v.size() == size && proCount<20) {
				System.out.println(Thread.currentThread().getName() + " is waiting");
				v.wait();
			}	
			synchronized (v) {
				if(proCount<20) {
					v.notifyAll();
					System.out.println(Thread.currentThread().getName() +"  added " +(proCount));	
					v.add(proCount++);
				}
			}
		}
	}
}

class Consumer implements Runnable{
	ArrayList<Integer> v;
	int size;
	static volatile int conCount =1;
	public Consumer(ArrayList<Integer> v, int size) {
		this.size = size;
		this.v = v;
	}
	
	public void run() {
		while(conCount<20)
		try {
			consume();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void consume() throws InterruptedException{		
		synchronized (v) {	
			while(v.size() == 0 && conCount<20) {
				System.out.println(Thread.currentThread().getName() +" is waiting");
				v.wait();
			}	
		}
		synchronized (v) {
			if(conCount<20) {
				v.notifyAll();
				System.out.println(Thread.currentThread().getName() +" is consuming "+ v.get(0));			
				v.remove(0);
				conCount++;	
			}
		}
	}
}
public class MultipleProducersConsumers {

	public static void main(String[] args) {
		ArrayList<Integer> v = new ArrayList<>();
		int size = 5;
		for(int i=0;i<10;i++) {
			new Thread(new Producer(v, size), "Producer"+i).start();
			new Thread(new Consumer(v, size), "Consumer"+i).start();
		}
		
	}

}
