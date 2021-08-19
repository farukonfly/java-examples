package examples.designpattern.singleton;

public class SingletonLazy {

	private SingletonLazy(){}
	private static SingletonLazy instance = null;

	public static synchronized SingletonLazy getInstance(){
		if (null == instance){
			instance = new SingletonLazy();
		}
		return instance;
	}

}
