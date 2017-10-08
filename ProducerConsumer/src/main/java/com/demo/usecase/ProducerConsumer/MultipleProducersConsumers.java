package com.demo.usecase.ProducerConsumer;

import java.util.Vector;

class Producer implements Runnable{
	Vector<Integer> v;
	int size;
	static int proCount =1;
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
			while(v.size() == size) {
				System.out.println(Thread.currentThread().getName() + " is waiting");
				v.wait();
			}
			if(proCount <= 20) {
				v.notifyAll();
				System.out.println(Thread.currentThread().getName() +"  added " +proCount);
				v.add(proCount);
				proCount++;

			}
		}
	}
	
}

class Consumer implements Runnable{
	Vector<Integer> v;
	int size;
	static int conCount =1;
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
			while(v.size() == 0) {
				System.out.println(Thread.currentThread().getName() +" is waiting");
				v.wait();
			}		
			if(conCount<=20) {
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
		Vector<Integer> v = new Vector<>();
		int size = 5;
		for(int i=0;i<2;i++) {
			new Thread(new Producer(v, size), "Producer"+i).start();
			new Thread(new Consumer(v, size), "Consumer"+i).start();
		}
		
	}

}
