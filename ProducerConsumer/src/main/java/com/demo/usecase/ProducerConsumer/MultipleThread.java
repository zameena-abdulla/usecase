package com.demo.usecase.ProducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadPrinter{
	boolean t1Printed = true;
	boolean t2Printed = false;
	boolean t3Printed = false;	
	AtomicInteger n = new AtomicInteger(1);
	
	synchronized void print() throws InterruptedException{
		String name = Thread.currentThread().getName();
		if(!t1Printed && name.equalsIgnoreCase("t1")) {
			wait();
		}
		else {
			t1Printed = false;
			t2Printed = true;
			t3Printed = false;
			System.out.println(n.getAndIncrement());
		}
		if(!t2Printed && name.equalsIgnoreCase("t2")) {
			wait();
		}
		else {
			t1Printed = false;
			t2Printed = false;
			t3Printed = true;
			System.out.println(n.getAndIncrement());
		}
		if(!t3Printed && name.equalsIgnoreCase("t3")) {
			wait();
		}
		else {
			t1Printed = true;
			t2Printed = false;
			t3Printed = false;
			System.out.println(n.getAndIncrement());
		}
	}
}

class PrinterThread{
	ThreadPrinter p;
	public PrinterThread(ThreadPrinter p) {
		this.p = p;
	}
	
	public void run() {
		for(int i=0;i<20;i++) {
			try {
				p.print();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class MultipleThread 
{
    public static void main( String[] args ){
    	ThreadPrinter p = new ThreadPrinter();
    	
    }
}
