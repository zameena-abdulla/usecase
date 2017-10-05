package usecase.FibanocciSeries;

public class FibanocciSeriesRecursive {
	
	static int n1= 0;
	static int n2= 1;
	static void printFibanocciSeries(int count) {
		if(count > 0) {
			int n3 = n1+n2;
			System.out.println(n3);
			n1= n2;
			n2 =n3;
			printFibanocciSeries(count-1);
		}		
	}
	
	public static void main(String[] args) {
		int count =5;
		System.out.println(n1);
		System.out.println(n2);
		printFibanocciSeries(count-2);
		
	}

}
