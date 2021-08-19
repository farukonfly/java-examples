package examples.util;
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写
public class StringUtils {
	public  static  boolean isPalindrome(String str) {
		//统一转成小写字符append进stringbuffer
		StringBuffer stringBuffer = new StringBuffer();
		char[] ch = str.toCharArray();
		for(int i = 0; i < str.length();i++) {
			if (Character.isLetterOrDigit(ch[i])) {
				stringBuffer.append(Character.toLowerCase(ch[i]));
			}
		}
		//通过双指针对比首位字符，如果相同count++
		//当count的值与stringBuffer的长度的一半相同，则为回文串
		int count = 0;
		int count2 = stringBuffer.length() / 2;
		for (int j = 0; j < count2; j++) {
			if(stringBuffer.charAt(j) == stringBuffer.charAt(stringBuffer.length() - j - 1)){
				count++;
			}
		}
		if(count == count2){
			return true;
		}
		return false;


	}
}
