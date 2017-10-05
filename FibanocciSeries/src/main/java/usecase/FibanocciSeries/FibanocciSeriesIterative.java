package usecase.FibanocciSeries;


public class FibanocciSeriesIterative {
	
	int n1 = 0;
	int n2 = 1;
	
	void printFibanocciSeries(int n) {
		int n3 =0;
		System.out.println(n1);
		System.out.println(n2);
		for(int i=0;i<n-2;i++) {
			n3 = n1+n2;
			System.out.println(n3);
			n1 = n2;
			n2 = n3;			
		}		
	}

	
	public static void main(String[] args) {
		FibanocciSeriesIterative fi = new FibanocciSeriesIterative();
		fi.printFibanocciSeries(10);
		
	}

}
