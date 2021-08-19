package examples.jdk8.reference;

import java.io.IOException;

//强引用
public class NormalReference {

	public static void main(String[] args) throws IOException {
		M m = new M(); //强引用
		m = null ;
		System.gc();
		System.in.read();
	}
}

class M {

	@Override
	protected void finalize() throws Throwable {
		//开发中不要重写，此处只是为了观察到对象是否被垃圾回收器清理了
		System.out.println("finalize");
	}

}