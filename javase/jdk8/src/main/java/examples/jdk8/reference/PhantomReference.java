package examples.jdk8.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

//虚引用 最弱的引用
//get永远是null
//管理堆外内存，即不在JVM的内存中，虚引用指向DirectByteBuffer(直接缓冲区) -Xmx10M
public class PhantomReference {
	private static final List<Object> LIST = new LinkedList<>();
	private static final ReferenceQueue<MMM> QUEUE = new ReferenceQueue<>();

	public static void main(String[] args) {
		java.lang.ref.PhantomReference<MMM> phantomReference = new java.lang.ref.PhantomReference<>(new MMM(), QUEUE);
		new Thread(() -> {
			while (true) {
				LIST.add(new byte[1024 * 1024]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				System.out.println(phantomReference.get()); //永远是null，弱引用没被回收时还能get到对象
			}
		}).start();

		new Thread(() -> {
			while (true) {
				Reference<? extends MMM> poll = QUEUE.poll();
				if(poll!=null) {
					System.out.println("---虚引用对象被jvm回收---"+poll);
				}
			}
		}).start();

		try {
			Thread.sleep(500);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MMM {
	@Override
	protected void finalize() throws Throwable {
		// 开发中不要重写，此处只是为了观察到对象是否被垃圾回收器清理了
		System.out.println("finalize");
	}
}
