package com.demo.usecase.ProducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadPrinter{
	boolean printT1 = true;
	boolean printT2 = false;
	boolean printT3 = false;	
	AtomicInteger n = new AtomicInteger(1);
	
	synchronized void print() throws InterruptedException{
		String name = Thread.currentThread().getName();
		if(name.equalsIgnoreCase("t1")) {
			if(!printT1) {
				wait();
			}
			
			else {
				printT1 = false;
				printT2 = true;
				printT3 = false;
				System.out.println(name +" is printing "+n.getAndIncrement());
				notifyAll();
			}
		}
		if(name.equalsIgnoreCase("t2")) {
			if(!printT2) {

				wait();
			}
			else {
				printT1 = false;
				printT2 = false;
				printT3 = true;
				System.out.println(name +" is printing "+n.getAndIncrement());
				notifyAll();
			}
		}
		if(name.equalsIgnoreCase("t3")) {
			if(!printT3) {
				wait();
			}
			else {
				printT1 = true;
				printT2 = false;
				printT3 = false;
				System.out.println(name +" is printing "+n.getAndIncrement());
				notifyAll();
			}
		}
	}
		
}

class PrinterThread implements Runnable{
	ThreadPrinter p;
	public PrinterThread(ThreadPrinter p) {
		this.p = p;
	}
	
	public void run() {
		while(true) {
			try {
				p.print();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
public class MultipleThread 
{
    public static void main( String[] args ){
    	ThreadPrinter p = new ThreadPrinter();
    	Thread one = new Thread(new PrinterThread(p), "t1");
    	Thread two = new Thread(new PrinterThread(p), "t2");
    	Thread three = new Thread(new PrinterThread(p),"t3");
    	one.start();
    	two.start();
    	three.start();
   
    }
}
