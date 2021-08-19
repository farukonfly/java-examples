package examples.jdk8.concurrent.disorder;

/**
 * CPU的乱序执行
 * @author farukon
 *
 */
public class Disorder {
	private static int x = 0, y = 0;
	private static int a = 0, b = 0;

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		for(;;) {
			i++;
			Thread one = new Thread(()->{
				a = 1;
				x = b;
			});
			Thread other = new Thread(()->{
				b = 1;
				y = a;
			});
			one.start();other.start();
			one.join();other.join();
			String result = "第"+i+"次("+x+","+y+")";
			if(x==0&&y==0) {
				System.err.println(result); //输出此行即证明CPU的乱序执行
				break ;
			}
		}
	}

}
