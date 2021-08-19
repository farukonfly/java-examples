package examples.newfeature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MethodReference {
	public static void main(String[] args) {
		methodReference_classStaticMethod();
	}

	// 静态方法引用：Class::staticMethodName
	public static void methodReference_classStaticMethod() {
		Function<List<Integer>, Integer> maxFn = Collections::max;
		// 等价于 Lambda 表达式：
		// Function<List<Integer>, Integer> maxFn = (numbers) ->
		// Collections.max(numbers);
		System.out.println(maxFn.apply(Arrays.asList(1, 10, 3, 5)));
	}

	public static void methodReference_instanceInstanceMethod() {

	}

}


class ComparisonProvider{

	public int compareByName(Person a, Person b){
		return a.getName().compareTo(b.getName());
	}

	public int compareByAge(Person a, Person b){
		return a.getBirthday().compareTo(b.getBirthday());
	}
}


