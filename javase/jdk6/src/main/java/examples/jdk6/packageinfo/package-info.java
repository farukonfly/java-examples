@PkgAnnotation  
package examples.jdk6.packageinfo; 

//包类 包访问权限
class PkgClass{
	public void method_pkg() {
		System.out.println("method_pkg");
	}
}

//包常量
class PkgConst{
	static final String PACKAGE_CONST="ABC";
}
