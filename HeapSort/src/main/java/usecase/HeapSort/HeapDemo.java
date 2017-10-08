package usecase.HeapSort;

import java.util.Vector;

/**
 * Hello world!
 *
 */
class MinHeap{
	
	private Vector<Integer> v;
	
	public MinHeap() {
		v = new Vector<Integer>();
	}
	
	private int parent(int index) {
		if(index == 0) {
			return index;
		}
		return (index-1)/2;
	}
	
	private int left(int index) {
		return 2*index + 1;
	}
	
	private int right(int index) {
		return 2*index + 2;
	}
	
	public void add(int i) {
		v.add(i);
		int index = v.size()-1;
		heapifyUp(index);
	}
	
	private void heapifyUp(int index) {
		if(index>0 && v.get(parent(index)) > v.get(index)) {
			swap(index, parent(index));
			heapifyUp(parent(index));
		}
	}
	
	private void heapifyDown(int index){
		int smallest = index;
		int left = left(index);
		int right = right(index);
		
		if(left<v.size() && v.get(left) < v.get(index)){
			smallest = left;
		}
		if(right<v.size() && v.get(right) < v.get(smallest)) {
			smallest = right;
		}
		if(smallest  != index) {
			swap(index, smallest);
			heapifyDown(smallest);
		}
		
	}
	
	private void swap(int i, int j) {
		int temp1 = v.get(i);
		int temp2 = v.get(j);		
		v.setElementAt(temp1, j);
		v.setElementAt(temp2, i);		
	}
	
	public Integer poll(){
		try {
			if(v.size() == 0) {
				throw new Exception();
			}			
			int first = v.get(0);
			int last = v.get(v.size()-1);
			v.setElementAt(last, 0);
			v.remove(v.size()-1);
			heapifyDown(0);
			return first; 
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

public class HeapDemo 
{
    public static void main( String[] args )
    {
        int[] a = {3,2,4,5,1,6,7,9};
        MinHeap m = new MinHeap();
        for(int i=0;i<a.length;i++) {
        	m.add(a[i]);
        }
        for(int i=0;i<a.length;i++) {
        	System.out.println(m.poll());
        }
     }
}
