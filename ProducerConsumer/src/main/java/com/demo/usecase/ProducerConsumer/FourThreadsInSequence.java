package com.demo.usecase.ProducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadPrinter2{
	boolean[] flagArray;
	int noOfThreads;
	//AtomicInteger n = new AtomicInteger(1);'
	static volatile int n =1;
	
	public ThreadPrinter2(int noOfThreads) {
		this.noOfThreads = noOfThreads;
		flagArray = new boolean[noOfThreads];
		flagArray[0] = true;  
	}
	
	synchronized void printer() throws InterruptedException{
		while(n < 20) {
			String tname = Thread.currentThread().getName();
			int id = Integer.parseInt(tname);
			while(!flagArray[id-1]) {
				wait();
			}
			notifyAll();
			System.out.println("Thread"+id+" "+n++);
			flagArray[id-1] = false;
			flagArray[id%noOfThreads] = true;
		}


	}
}
public class FourThreadsInSequence{
    static class MyThread2 implements Runnable{
    	ThreadPrinter2 p;
    	public MyThread2(ThreadPrinter2 p) {
			this.p = p;
		}
    	
    	public void run() {
    		try {
				p.printer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
	public static void main(String[] args) {
		ThreadPrinter2 p = new ThreadPrinter2(4);
		new Thread(new MyThread2(p), "1").start();
		new Thread(new MyThread2(p), "2").start();
		new Thread(new MyThread2(p), "3").start();
		new Thread(new MyThread2(p), "4").start();
		
 
	}

}
