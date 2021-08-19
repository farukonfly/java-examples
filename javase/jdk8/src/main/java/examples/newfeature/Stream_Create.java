package examples.newfeature;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Stream_Create {
	public static void main(String[] args) {
		//1.Collection.stream()
		List<String> list = Arrays.asList("1","2","3");
		Stream<String> stream_1 = list.stream();

		//2.使用java.util.Arrays.stream(T[] array)创建数组流
		String[] arr = {"1","2","3","4","5"};
		Stream<String> stream_2 =  Arrays.stream(arr);

		//3.使用Stream的静态方法：of(),iterate().generate()
		Stream<Integer> insStream = Stream.of(1,2,3,4,5);
		Stream<Integer> insStream2 = Stream.iterate(0, x->x+3).limit(3);

		AtomicInteger m = new AtomicInteger(10);
		Stream<Integer> insStream3 = Stream.generate(()->m.getAndIncrement()).limit(3);
	}

}
