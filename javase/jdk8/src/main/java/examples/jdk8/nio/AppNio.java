package examples.jdk8.nio;

import java.nio.ByteBuffer;

/**
 * mark <= position <= limit <= capacity 
 * capacity:容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit:界限，表示缓冲区中可以操作数据大小（limit后的数据不能进行读写）
 * position:位置，表示缓冲区中正在操作的位置
 * mark:标记，表示记录当年position的位置，可以通过reset()恢复到mark的位置
 * 
 *非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 *直接缓冲区：  通过allcacateDirect()方法分配，将缓冲区建立在物理内存中，可以提高效率
 * @author farukon
 *
 */
public class AppNio {
	public static void main(String[] args) {
		AppNio.markTest();
	}

	public static void markTest() {
		String str = "abcde";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(str.getBytes());
		buffer.flip();
		byte[] dist = new byte[buffer.limit()];
		buffer.get(dist,0,2);
		System.out.println(new String(dist,0,2)); //ab
		buffer.mark();//表示记录当年position的位置，可以通过reset()恢复到mark的位置
	}
	public static void bufferTest() {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);// 分配缓冲区大小，1024个字节
		System.out.println(buf);
		// 利用put向缓冲区写入数据
		buf.put(str.getBytes());
		System.out.println("buf.put()之后：");
		System.out.println(buf);
		buf.flip(); // 切换到读模式
		System.out.println("buf.flip()之后：");
		System.out.println(buf);
		byte[] dist = new byte[buf.limit()];
		buf.get(dist); //从buffer读取数据
		System.out.println(new String(dist, 0, dist.length));
		System.out.println("buf.get(dist)--->"+buf);

		//rewind 可重复读
		buf.rewind();
		System.out.println("buf.rewind--->"+buf);

		//clear 清空缓冲区,但是缓冲区中数据依然存在，但是数据处于被遗忘的状态
		buf.clear();
		System.out.println("buf.clear-->"+buf);
		System.out.println((char)buf.get());
	}
}
