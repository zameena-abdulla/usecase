package com.demo.usecase.ProducerConsumer;

import java.util.Vector;
import java.util.concurrent.Semaphore;

class CustomQueue{
	Vector<Integer> v;
	int maxSize;
	Semaphore semProd;
	Semaphore semCons;
	
	CustomQueue(int maxSize){
		this.maxSize = maxSize;
		this.v = new Vector<Integer>();
		semProd = new Semaphore(maxSize);
		semCons = new Semaphore(0);
	}
	
	
	public void put(int i) {		
		try {
			semProd.acquire();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Producer producing "+i);
		v.add(i);
		semCons.release();		
	}
	
	public void get() {
		try {
			semCons.acquire();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Consumer consuming "+ v.get(0));
		v.remove(0);
		semProd.release();
	}
}

class Producer2 implements Runnable{
	CustomQueue q;
	public Producer2(CustomQueue q) {
		this.q = q;
	}
	public void run() {
		for(int i=1;i<=20;i++) {
			q.put(i);
		}		
	}	
	
}

class Consumer2 implements Runnable{	
	CustomQueue q;
	public Consumer2(CustomQueue q) {
		this.q = q;
	}
	public void run() {
		for(int i=1;i<=20;i++) {
			q.get();
		}		
	}		
}

public class ProducerConsumerSemaphore {
	public static void main(String args[]) {
		CustomQueue q = new CustomQueue(5);		
		Thread prod = new Thread(new Producer2(q));
		Thread cons = new Thread(new Consumer2(q));	
		prod.start();
		cons.start();
	}
}
