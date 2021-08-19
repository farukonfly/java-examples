package examples.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//java -Xms200M -Xmx200M -XX:+PrintGC com.mashibing.jvm.gc.T15_FullGC_Problem01
public class FullGC_Problem {
	private static class CardInfo {
		BigDecimal price = new BigDecimal(0.0);
		String name = "张三";
		int age = 5;
		Date birthDate = new Date();

		public void m() {

		}
	}

	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
			new ThreadPoolExecutor.DiscardOldestPolicy());

	private static List<CardInfo> getAllCardInfo() {
		List<CardInfo> taskList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			CardInfo ci = new CardInfo();
			taskList.add(ci);
		}
		return taskList;
	}

	private static void modelFit() {
		List<CardInfo> taskList = getAllCardInfo();
		taskList.forEach(info -> {
			executor.scheduleWithFixedDelay(() -> {
				info.m();
			}, 2, 3, TimeUnit.SECONDS);
		});
	}

	public static void main(String[] args) throws InterruptedException {
		executor.setMaximumPoolSize(50);
		for (;;) {
			modelFit();
			Thread.sleep(100);
		}
	}
}
