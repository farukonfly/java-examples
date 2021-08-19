package examples.newfeature.record;

import java.io.Serializable;

/**
 * FirstPreveion #jdk14
 * jdk16成为标准
 * @author farukon
 *
 */
public record Person(String name,int age) implements Serializable{

	public Person(String name,int age) {
		//可进行校验
		this.age = 0;
		this.name = "";
		
	}
	public static void main(String[] args) {
		var p = new Person("Person1", 25);
		System.out.println(p);
	}
}
