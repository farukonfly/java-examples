package examples.jdk8.concurrent.volatilekey;

/**
 * found 不用volatile修饰 程序将永远无法退出
 * @author farukon
 *
 */
public class Volatile {
	public static  volatile   int found = 0; 

	public static void main(String[] args) {
		new Thread(() -> {
			while (0 == found) {

			}
			System.out.println("found=0->线程结束");  //found 不使用volatile修饰，将永远不可达本条语句
		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			found = 1;
			System.out.println("found=1->线程结束");
		}).start();

	}

}
