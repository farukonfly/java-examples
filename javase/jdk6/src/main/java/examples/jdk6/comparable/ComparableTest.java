package examples.jdk6.comparable;

import java.util.SortedSet;
import java.util.TreeSet;

class ComparableTest {
	public static void main(String[] args) {
		SortedSet<A> sortedSet = new TreeSet<A>();
		sortedSet.add(new A(1));
		sortedSet.add(new A(2));
		System.out.println(sortedSet);
	}
}

class A implements Comparable<A>{

	int field = 0 ;

	@Override
	public int compareTo(A o) {
		return -1;
	}

	public A(int field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "A [field=" + this.field + "]";
	}



}
