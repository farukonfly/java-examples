package examples.classloader;

import java.util.stream.Stream;

public class ClassLoaderApp {

	public static void main(String[] args) throws ClassNotFoundException {
		loadClassByHand();
	}

	public static void classloaderPath() {
		// Bootstrap加载
		System.out.println("Bootstrap加载");
		Stream.of(System.getProperty("sun.boot.class.path").split(";")).forEach(System.out::println);

		// Ext加载
		System.out.println("Ext加载");
		Stream.of(System.getProperty("java.ext.dirs").split(";")).forEach(System.out::println);

		// App加载
		System.out.println("App加载");
		Stream.of(System.getProperty("java.class.path").split(";")).forEach(System.out::println);
	}

	public static void loadClassByHand() throws ClassNotFoundException {
		System.out.println(ClassLoaderApp.class.getClassLoader().loadClass("examples.classloader.ClassLoaderTest"));
	}
}
