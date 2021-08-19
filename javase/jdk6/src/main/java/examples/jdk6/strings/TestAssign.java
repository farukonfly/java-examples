package examples.jdk6.strings;

public class TestAssign {

	public static void main(String[] args) {
		String str2 = "str"+"01";
		str2.intern();
		String str1 = "str01";
		System.out.println(str1==str2);
	}

}
