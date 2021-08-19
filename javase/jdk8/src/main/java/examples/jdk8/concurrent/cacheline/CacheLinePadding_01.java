package examples.jdk8.concurrent.cacheline;

/**
 * CPU按块读取速度比较块，缓存行为64个字节(Byte)
 * arr数组为16个字节，两个线程去读，一个线程改arr[0]和一个线程改arr[1]
 * 16字节<缓存行，2个线程在操作同一块内存，而x又使用volatile修饰，需要保持线程可见性(数据一旦修改，缓存行即需要和主内存同步)，从而导致需要不停的将线程数据同步到主内存
 *
 * @author farukon
 *
 */
public class CacheLinePadding_01 {
	private static class T {
		public volatile long x = 0L; // long 8个字节
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
