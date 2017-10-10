package com.demo.usecase.ProducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadPrinter1{
	
	boolean printZero = true;
	boolean printOdd = false;
	boolean printEven = false;
	AtomicInteger n = new AtomicInteger(1);
	
	synchronized void print() throws InterruptedException{
		String name = Thread.currentThread().getName();
		if(name.equals("zero")) {
			if(!printZero) {
				wait();
			}
			else {
				notifyAll();
				System.out.print(0);
				printZero = false;
				if(n.get()%2 == 1) {
					printOdd = true;
				}
				if(n.get()%2 == 0) {
					printEven = true;
				}
			}
		}
		if(name.equals("odd")) {
			if(!printOdd) {
				wait();
			}
			else {
				notifyAll();
				System.out.print(n.getAndIncrement());
				printZero = true;
				printOdd = false;
				
			}
		}
		
		if(name.equals("even")) {
			if(!printEven) {
				wait();
			}
			else {
				notifyAll();
				System.out.print(n.getAndIncrement());
				printZero = true;
				printEven = false;
			}
		}
		
	}
	
}

class NumberPrinterThread implements Runnable{
	ThreadPrinter1 p;
	public NumberPrinterThread(ThreadPrinter1 p) {
		this.p = p;
	}
	public void run() {
		while(p.n.get() <7) {
			try {
				p.print();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ThreeThreadDemo {
	public static void main(String args[]) {
		ThreadPrinter1 p = new ThreadPrinter1();
		Thread zero = new Thread(new NumberPrinterThread(p), "zero");
		Thread odd = new Thread(new NumberPrinterThread(p), "odd");
		Thread even = new Thread(new NumberPrinterThread(p), "even");
		zero.start();
		odd.start();
		even.start();
	}
	
	
	
	
	
}
