package com.demo.usecase.ProducerConsumer;

import java.util.Vector;

class Producer implements Runnable{
	Vector<Integer> v;
	int size;
	static int count =1;
	public Producer(Vector<Integer> v, int size) {		
		this.v = v;
		this.size = size;
	}
	
	public void run() {
		while(count <= 20) {
			try {
				produce(count);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void produce(int i) throws InterruptedException{
		while(v.size() == size) {
			synchronized (v) {
				System.out.println(Thread.currentThread().getName() + " is waiting");
				v.wait();
			}
		}
		synchronized (v) {
			v.notifyAll();
			System.out.println(Thread.currentThread().getName() +"  added " +count);
			v.add(count++);
			
		}
	}
	
}

class Consumer implements Runnable{
	Vector<Integer> v;
	int size;
	
	public Consumer(Vector<Integer> v, int size) {
		this.size = size;
		this.v = v;
	}
	
	public void run() {
		while(true) {
			try {
				consume();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void consume() throws InterruptedException{
		while(v.size() == 0) {
			synchronized (v) {
				System.out.println(Thread.currentThread().getName() +" is waiting");
				v.wait();
			}
		}
		synchronized (v) {
			v.notifyAll();
			System.out.println(Thread.currentThread().getName() +" is consuming "+ v.get(0));			
			v.remove(0);
			
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
