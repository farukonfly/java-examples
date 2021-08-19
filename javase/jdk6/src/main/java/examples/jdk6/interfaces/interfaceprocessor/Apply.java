//: interfaces/interfaceprocessor/Apply.java
package examples.jdk6.interfaces.interfaceprocessor;
import static examples.jdk6.net.mindview.util.Print.*;

public class Apply {
  public static void process(Processor p, Object s) {
    print("Using Processor " + p.name());
    print(p.process(s));
  }
} ///:~
