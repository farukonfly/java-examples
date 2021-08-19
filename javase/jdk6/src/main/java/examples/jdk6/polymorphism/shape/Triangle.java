//: polymorphism/shape/Triangle.java
package examples.jdk6.polymorphism.shape;
import static examples.jdk6.net.mindview.util.Print.*;

public class Triangle extends Shape {
  public void draw() { print("Triangle.draw()"); }
  public void erase() { print("Triangle.erase()"); }
} ///:~
