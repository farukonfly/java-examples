package examples.jdk8.reflect;

import java.util.Arrays;

public class ReflectAndAnnotationTest {
	public static void main(String[] args) {
		Class<Sub> clazz = Sub.class;
		// 所有public属性，包括继承而来的
		System.out.println("getFields:" + Arrays.toString(clazz.getFields()));

		// 仅自身属性，不包括继承而来，但会获取到public,private,protected的所有属性
		System.out.println("getDeclaredFields:" + Arrays.toString(clazz.getDeclaredFields()));

		// 所有public方法，包括继承而来的
		System.out.println("getMethods:" + Arrays.toString(clazz.getMethods()));

		// 仅自身方法，不包括继承而来，但会获取到public,private,protected的所有方法
		System.out.println("getDeclaredMethods:" + Arrays.toString(clazz.getDeclaredMethods()));

		// 取不到私有构造方法
		System.out.println("getConstructors:" + Arrays.toString(clazz.getConstructors()));

		System.out.println("getDeclaredConstructors:" + Arrays.toString(clazz.getDeclaredConstructors()));

		// 注解DBTable2是否存在于元素上
		System.out.println("@BTable是否在Sub.classs上:" + clazz.isAnnotationPresent(BTable.class));
		System.out.println("返回@BTable注解：" + clazz.getAnnotation(BTable.class));

		// 可以打印出当前类的注解和父类的注解
		System.out.println("getAnnotations:" + Arrays.toString(clazz.getAnnotations()));
		// 只会打印出当前类的注解
		System.out.println("getDeclaredAnnotations:" + Arrays.toString(clazz.getDeclaredAnnotations()));
		/**
		 * 
		 * 一个子类想获取到父类上的注解信息， 那么必须在父类上使用的注解上面 加上@Inherit关键字
		 * 
		 */

	}
}
