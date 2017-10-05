package usecase.HashmapSort;

import java.util.HashMap;

class Student{
	static int counter =0;
	int id;
	String name;
	int age;
	Student(String name, int age){
		this.id = counter++;
		this.name = name;
		this.age = age;
	}
}
public class HashmapSort {

	public static void main(String[] args) {
		HashMap<Integer , Student> shm = new HashMap<>();
		int age =10;
		String name = "a";
		for(int i=0;i<10;i++){
			shm.put(i, new Student(name+1, age++));
		}
		System.out.println("Hi");

	}

}
