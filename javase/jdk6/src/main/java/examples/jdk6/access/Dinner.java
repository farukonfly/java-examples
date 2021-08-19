package examples.jdk6.access;
import examples.jdk6.access.cookie2.Cookie;

public class Dinner {
  public static void main(String[] args) {
    Cookie x = new Cookie();
    //! x.bite(); // Can't access
  }
} /* Output:
Cookie constructor
*///:~
