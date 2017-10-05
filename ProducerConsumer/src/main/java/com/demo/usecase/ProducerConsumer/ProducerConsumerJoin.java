package com.demo.usecase.ProducerConsumer;

import java.util.Vector;

class CustomSharedResource{
	int content;
	boolean isEmpty = true;
	
	void put(int i) {
		
	}
	
	void get(){
		
	}
	
}

class Producer3{
	Vector<Integer> v;
	
	public Producer3(Vector<Integer> v)  {
		this.v = v;
		
	}
	
}

class Consumer3{
	Vector<Integer> v;
	
	public Consumer3(Vector<Integer> v)  {
		this.v = v;
		
	}
	
}
public class ProducerConsumerJoin {

}
