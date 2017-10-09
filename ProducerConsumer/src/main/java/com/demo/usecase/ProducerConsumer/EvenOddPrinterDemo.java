package com.demo.usecase.ProducerConsumer;

class EvenOddPriter{
	private boolean isEvenPrinted = true;
	
	public synchronized void printOdd(int i) throws InterruptedException{
		while(!isEvenPrinted) {
			this.wait();
		}
		System.out.println(i);
		notifyAll();
		isEvenPrinted = false;
	}
	
	public synchronized void printEven(int i) throws InterruptedException{
		while(isEvenPrinted) {
			this.wait();
		}
		System.out.println(i);
		notifyAll();
		isEvenPrinted = true;
	}
	
}

class OddPrinter implements Runnable{
	EvenOddPriter p;	
	public OddPrinter(EvenOddPriter p) {
		this.p = p;
	}
	public void run() {
		for(int i=1;i<20;i=i+2) {
			try {
			   p.printOdd(i);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}	
}

class EvenPrinter implements Runnable{
	EvenOddPriter p;	
	public EvenPrinter(EvenOddPriter p) {
		this.p = p;
	}
	public void run() {
		for(int i=2;i<20;i=i+2) {
			try {
			   p.printEven(i);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}	
}
public class EvenOddPrinterDemo {

	public static void main(String[] args) {
		EvenOddPriter p = new EvenOddPriter();
		Thread t1 = new Thread(new  OddPrinter(p));
		Thread t2 = new Thread(new EvenPrinter(p));
		t1.start();
		t2.start();

	}

}
