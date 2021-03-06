package examples.jdk6.strings;

/**
 * 
 * @author farukon
 *一、new String都是在堆上创建字符串对象。
 *	当调用 intern() 方法时，编译器会将字符串添加到常量池中（stringTable维护），并返回指向该常量的引用
 *二、通过字面量赋值创建字符串（如：String str=”twm”）时，会先在常量池中查找是否存在相同的字符串，
 *	若存在，则将栈中的引用直接指向该字符串；
 *	若不存在，则在常量池中生成一个字符串，再将栈中的引用指向该字符串。
 *三、常量字符串的“+”操作，编译阶段直接会合成为一个字符串。
 *	如string str=”JA”+”VA”，在编译阶段会直接合并成语句String str=”JAVA”，于是会去常量池中查找是否存在”JAVA”,从而进行创建或引用
 *四、对于final字段，编译期直接进行了常量替换（而对于非final字段则是在运行期进行赋值处理的）。
	final String str1=”ja”;
	final String str2=”va”;
	String str3=str1+str2;
	在编译时，直接替换成了String str3=”ja”+”va”，根据第三条规则，再次替换成String str3=”JAVA”
  五、常量字符串和变量拼接时（如：String str3=baseStr + “01”;）会调用stringBuilder.append()在堆上创建新的对象
  六、JDK 1.7后，intern方法还是会先去查询常量池中是否有已经存在，如果存在，则返回常量池中的引用，这一点与之前没有区别，
   区别在于，如果在常量池找不到对应的字符串，则不会再将字符串拷贝到常量池，而只是在常量池中生成一个对原字符串的引用。
   简单的说，就是往常量池放的东西变了：原来在常量池中找不到时，复制一个副本放到常量池，1.7后则是将在堆上的地址引用复制到常量池
 */
public class InternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InternTest.test();
	}

	public static void test() {
		String s = new String("1");
		s.intern();
		String s2 = "1";
		System.out.println(s==s2); //false

		String s3 = new String("1")+new String("1");
		s3.intern();
		String s4 = "11";
		System.out.println(s3==s4);//jdk1.6 false jdk1.7及以上 true
	}


}
