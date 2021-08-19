package examples.jdk8.concurrent.dcl;

//DCL(Double Check Lock)
public class SingletonLazyDCL {

	private SingletonLazyDCL(){}
	private static volatile SingletonLazyDCL instance = null; //通过加volatile防止指令重排序

	public static  SingletonLazyDCL getInstance(){
		if (null == instance){
			synchronized (SingletonLazyDCL.class) {
				if(null==instance) {
					instance = new SingletonLazyDCL();
				}
			}

		}
		return instance;
	}

}
