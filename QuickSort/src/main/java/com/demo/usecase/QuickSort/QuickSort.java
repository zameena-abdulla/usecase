package com.demo.usecase.QuickSort;


public class QuickSort 
{
	public void quickSort(int[] a) {
		partition(0, a.length-1, a);
	}
	
	private void partition(int low, int high, int[] a) {
		int i=low;
		int j=high;
		int pivot = a[high];
		while(i<=j) {
			while(a[i]<pivot) {
				i++;
			}
			while(a[j]>pivot) {
				j--;
			}
			if(i<=j) {
				int temp = a[i];
				a[i]=a[j];
				a[j]=temp;
				i++;
				j--;
			}
		}
		if(low<j) {
		    partition(low, j, a);
		}
		if(i<high) {
			partition(i, high, a);
		}
		
	}
    public static void main( String[] args )
    {
        int[] a = {5,9,2,8,7,3,4,2};
        QuickSort q = new QuickSort();
        q.quickSort(a);
        
        for(int i=0;i<a.length;i++) {
        	System.out.println(a[i]);
        }
    }
}
