package com.demo.usecase.ProducerConsumer;

import java.util.Vector;

class Producer1 implements Runnable{
	Vector<Integer> v;
	int size;
	
	public Producer1(Vector<Integer> v, int size) {
		this.v = v;
	    this.size = size;
	}
	public void run() {
		for(int i=1;i<20;i++) {
			try {
				produce(i);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void produce(int i) throws InterruptedException{		
		while(v.size() == size) {
			synchronized (v) {
				System.out.println("Producer is waiting");
				v.wait();
			}			
		}
		synchronized (v) {
			System.out.println("Producing value " +i);
			v.add(i);
			v.notify();	
		}		
	}
}

class Consumer1 implements Runnable{
	Vector<Integer> v;
	int size;
	public Consumer1(Vector<Integer> v, int size) {
		this.v = v;
		this.size = size;
	}
	
	public void run(){
		try {
			for(int i=1;i<20;i++) {
				consume();
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void consume() throws InterruptedException{
		
		while(v.size() ==0) {
			synchronized (v) {
				System.out.println("Consumer is waiting");
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
public class ProducerConsumerWaitNotify {
	public static void main(String[] args) {
		int size = 5;
		Vector<Integer> v = new Vector<>();
		Thread prod = new Thread(new Producer1(v, size));
		Thread cons = new Thread(new Consumer1(v, size));
		prod.start();
		cons.start();
	}

}


