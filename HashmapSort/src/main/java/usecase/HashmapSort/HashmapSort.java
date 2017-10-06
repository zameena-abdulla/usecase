package usecase.HashmapSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

class NameComparator implements Comparator<Student>{
	public int compare(Student s1, Student s2) {
		return s1.name.compareTo(s2.name);
	}
}
class Student implements Comparable<Student>{	
	int id;
	String name;
	int age;
	String phoneNumber;
	Student(int id , String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	void setPhonenumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	String getPhoneNumber() {
		return this.phoneNumber;
	}
	public int hashCode() {
		return 31*id;
	}
	
	public boolean equals(Student s) {
		if(this.id == s.id) {
			return true;
		}
		return false;
	}
	
	public int compareTo(Student s) {
		return this.age - s.age;
	}
}

public class HashmapSort {	
	
	public static void main(String[] args) {
		HashMap<Student, String> hm = new HashMap<>();
		
		Student s1 = new Student(11, "bbb", 14);
		s1.setPhonenumber("111222333");
		Student s2 = new Student(22, "aaa", 11);
		s2.setPhonenumber("222333444");	
		Student s3 = new Student(33, "ccc", 12);
		s3.setPhonenumber("333444555");	
		
		hm.put(s3, s3.getPhoneNumber());
		hm.put(s1, s1.getPhoneNumber());
		hm.put(s2, s2.getPhoneNumber());
		
		System.out.println("Unsorted Hashmap");
		for(Entry<Student, String> e : hm.entrySet()) {
			System.out.println(e.getKey().name +" "+e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Sorting by keys");
		System.out.println("Sorting hashmap based on natural ordering of keys.");
		TreeMap<Student, String> tm = new TreeMap<>(hm);
		for(Entry<Student, String> e : tm.entrySet()) {
			System.out.println(e.getKey().name +" "+e.getKey().age + " "+e.getValue());
		} 
		
		System.out.println("-------------------------");		
		System.out.println("Sorting hashmap based on custom ordering of keys.");
		TreeMap<Student, String> tm5 = new TreeMap<>(Collections.reverseOrder());
		tm5.putAll(hm);
		for(Entry<Student, String> e : tm5.entrySet()) {
			System.out.println(e.getKey().name +" "+e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");	
		ArrayList<Entry<Student, String>> al = new ArrayList<>(hm.entrySet());
		Collections.sort(al, new Comparator<Entry<Student, String>>() {
			@Override
			public int compare(Entry<Student, String> e1, Entry<Student, String> e2) {
				return e1.getKey().age - e2.getKey().age;
			}
		});
		
		System.out.println("-------------------------");	
		ArrayList<Entry<Student, String>> al1 = new ArrayList<>(hm.entrySet());
		Collections.sort(al, new Comparator<Entry<Student, String>>() {
			@Override
			public int compare(Entry<Student, String> e1, Entry<Student, String> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		
		System.out.println("Sorting by values");
		System.out.println("-------------------------");
		
		System.out.println("Treemap on natural ordering by age.");
		TreeMap<Student, String> tm1 = new TreeMap<>(hm);
		for(Entry<Student, String> e : tm1.entrySet()) {
			System.out.println(e.getKey().name +" "+e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Treemap on custom ordering by name.");
		TreeMap<Student, String> tm2 = new TreeMap<>(new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		});
		
		tm1.putAll(hm);		
		for(Entry<Student, String> e : tm2.entrySet()) {
			System.out.println(e.getKey().name +" "+e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Treemap based on value.");
		TreeMap<Student, String> tm3 = new TreeMap<>(new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return hm.get(s1).compareTo(hm.get(s2));
			}
		});
		tm2.putAll(hm);
		for(Entry<Student, String> e : tm3.entrySet()) {
			System.out.println(e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Java 8 sorting based on value.");
		Comparator<Entry<Student, String>> valueComp = 
				(e1,e2) -> e1.getValue().compareTo(e2.getValue());
		
		LinkedHashMap<Student, String> sm = hm.entrySet().stream().sorted(valueComp).collect
				(Collectors.toMap(Entry::getKey,
						Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		for(Entry<Student, String> e : sm.entrySet()) {
				System.out.println(e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Java 8 sorting based on natural ordering.");
		LinkedHashMap<Student, String> sm1 = hm.entrySet().stream().sorted(Entry.comparingByKey()).
				collect(Collectors.toMap(Entry::getKey, 
				Entry::getValue, (oldValue, newValue) -> oldValue,
				LinkedHashMap::new));
		for(Entry<Student, String> e : sm1.entrySet()) {
			System.out.println(e.getKey().age + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Java 8 sorting based on keys custom ordering.");
		LinkedHashMap<Student, String> sm2 = hm.entrySet().stream().sorted(Entry.comparingByKey(new NameComparator())).
				collect(Collectors.toMap(Entry::getKey, 
						Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		for(Entry<Student, String> e : sm1.entrySet()) {
			System.out.println(e.getKey().name + " "+e.getValue());
		}
		
		System.out.println("-------------------------");
		System.out.println("Java 8 sorting based on values.");
		LinkedHashMap<Student, String> sm3 =hm.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, 
				Entry::getValue, (oldValue, newValue) -> oldValue,
				LinkedHashMap::new));
		
		for(Entry<Student, String> e : sm3.entrySet()) {
			System.out.println(e.getKey().age + " "+e.getValue());
		}
		
		
	}

}
