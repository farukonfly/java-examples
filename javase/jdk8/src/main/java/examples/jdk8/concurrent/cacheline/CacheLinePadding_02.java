package examples.jdk8.concurrent.cacheline;

/**
 * CPU按块读取速度比较块，缓存行为64个字节(Byte)
 * 加了7*8给字节的填充位，数据位再占用8字节，刚好一个缓存行的大小
 * 从而arr[0]和arr[1]位于不同的缓存行，两个线程其实在操作两块独立的内容，不用去同步主内存，速度快
 * 并发框架：Disruptor
 * @author farukon
 *
 */
public class CacheLinePadding_02 {
	private static class Padding{
		public volatile long p1,p2,p3,p4,p5,p6,p7 ;
	}
	private static class T extends Padding {
		public volatile long x = 0L; // long 8个字节 T占64个字节
	}

	public static T[] arr = new T[2];

	static {
		arr[0] = new T();
		arr[1] = new T();
	}

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(() -> {
			for (long i = 0; i < 1000_0000L; i++) {
				arr[0].x = i;
			}

		});

		Thread t2 = new Thread(() -> {
			for (long i = 0; i < 1000_0000L; i++) {
				arr[1].x = i;
			}

		});

		final long start = System.nanoTime();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println((System.nanoTime()-start)/100_0000);

	}

}
