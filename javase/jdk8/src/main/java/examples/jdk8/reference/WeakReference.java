package examples.jdk8.reference;

//弱引用(一次性使用)
//一旦gc立马回收
public class WeakReference {

	public static void main(String[] args) {
		java.lang.ref.WeakReference<MM> m = new java.lang.ref.WeakReference<>(new MM());
		System.out.println(m.get());
		System.gc();
		System.out.println(m.get()); //null

		ThreadLocal<MM> t1 = new ThreadLocal<>();
		t1.set(new MM());
		t1.remove();
	}

}

class MM {
	@Override
	protected void finalize() throws Throwable {
		// 开发中不要重写，此处只是为了观察到对象是否被垃圾回收器清理了
		System.out.println("finalize");
	}
}