package examples.designpattern.singleton;

public class Singleton {

	private Singleton(){}
	private static Singleton singleton = new Singleton();

	public static Singleton getInstance(){
		return singleton;
	}
}
