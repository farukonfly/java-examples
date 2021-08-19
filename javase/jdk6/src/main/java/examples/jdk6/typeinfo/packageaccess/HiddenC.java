//: typeinfo/packageaccess/HiddenC.java
package examples.jdk6.typeinfo.packageaccess;
import static examples.jdk6.net.mindview.util.Print.*;

import examples.jdk6.typeinfo.interfacea.*;

class C implements A {
  public void f() { print("public C.f()"); }
  public void g() { print("public C.g()"); }
  void u() { print("package C.u()"); }
  protected void v() { print("protected C.v()"); }
  private void w() { print("private C.w()"); }
}

public class HiddenC {
  public static A makeA() { return new C(); }
} ///:~
