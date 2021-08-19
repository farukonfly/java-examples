package examples.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Stream_Collectors {
	public static void main(String[] args) {
		List<Person> list = new ArrayList<>(Arrays.asList(new Person("B"), new Person("A"), new Person("C")));
		toCollection_invokeConstruct(list);
	}

	public static void toCollection_invokeConstruct(List<Person> list) {
		Set<String> sortedName = list.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(sortedName);
	}
}
