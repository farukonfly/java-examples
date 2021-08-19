//: polymorphism/shape/Circle.java
package examples.jdk6.polymorphism.shape;
import static examples.jdk6.net.mindview.util.Print.*;

public class Circle extends Shape {
  public void draw() { print("Circle.draw()"); }
  public void erase() { print("Circle.erase()"); }
} ///:~
