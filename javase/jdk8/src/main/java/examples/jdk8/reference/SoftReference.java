package examples.jdk8.reference;

//软引用 -Xmx25M 主要用在缓存里面
//内存不够时回收
public class SoftReference {
	public static void main(String[] args) {
		java.lang.ref.SoftReference<byte[]> m = new java.lang.ref.SoftReference<byte[]>(new byte[1024*1024*10]);
		// m->SoftReference->byte[]
		System.out.println(m.get()); 
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(m.get());
		//再分配一个数组，heap将装不了，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
		byte[] b = new byte[1024*1024*15]; //15MB
		System.out.println(m.get()); //null
	}
}
