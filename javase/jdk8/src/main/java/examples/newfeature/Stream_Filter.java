package examples.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_Filter {

	public static void main(String[] args) {
		List<Float> list = new ArrayList<>(Arrays.asList(12.05f,999.05f,888.88f,2.00f,4200.00f));
		filter(list).forEach(System.out::println);
	}

	public static List<Float> filter(List<Float> list) {
		return list.stream().filter(t->t>2).collect(Collectors.toList());
	}

}
