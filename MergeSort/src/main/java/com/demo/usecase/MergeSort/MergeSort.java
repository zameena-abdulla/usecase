package com.demo.usecase.MergeSort;

/**
 * Hello world!
 *
 */

public class MergeSort 
{
	int[] temp;
	MergeSort(int size){
		temp = new int[size];
	}
	
	public void mergeSort(int[] a) {
		doMergeSort(0, a.length-1, a);
	}
	
	private void doMergeSort(int low, int high, int[] a) {
		if(low<high) {
			int mid=(low+high)/2;
			doMergeSort(low, mid, a);
			doMergeSort(mid+1, high, a);
			mergeParts(low, mid, high, a);
		}
	}
	
	private void mergeParts(int low, int mid, int high, int[] a) {
		for(int i=low; i<=high;i++) {
			temp[i] = a[i];
		}
		int i=low;
		int k=low;
		int j=mid+1;
		
		while(i<=mid && j<=high) {
			if(temp[i]<temp[j]) {
				a[k++] = temp[i++];
			}
			else{
				a[k++] = temp[j++];
			}
		}
		while(i<=mid) {
			a[k++] = temp[i++];
		}
		
	}
    public static void main( String[] args )
    {
    	int[] a = {2,5,11,6,7,8,9,4};
        MergeSort m = new MergeSort(a.length);
        m.mergeSort(a);
        for(int i=0;i<a.length;i++) {
        	System.out.println(a[i]);
        }
    }
}
