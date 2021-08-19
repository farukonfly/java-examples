package examples.jdk6.inherit;

public class C extends P {

	public C() {

	}

	public void method_c() {
		System.out.println("method_c");
		super.method_p();
		super.method_pp();
	}

	public static void main(String[] args) {
		C c = new C();
		c.method_c();
	}

}
