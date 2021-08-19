//: annotations/Testable.java
package examples.jdk6.annotations;
import examples.jdk6.net.mindview.atunit.Test;

public class Testable {
  public void execute() {
    System.out.println("Executing..");
  }
  @Test void testExecute() { execute(); }
} ///:~
