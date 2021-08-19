package examples.jdk6.inherit;

public class InheritTest {

	public static void main(String[] args) {
		new Sub_1();

	}

}

class Parent{
	public Parent() {
		System.out.println("Parent");
	}

}
class Sub_1 extends Parent{
	public Sub_1() {
		super();
		System.out.println("Sub_1");
	}
}
