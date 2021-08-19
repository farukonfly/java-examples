//: enumerated/menu/TypeOfFood.java
package examples.jdk6.enumerated.menu;
import static examples.jdk6.enumerated.menu.Food.*;

public class TypeOfFood {
  public static void main(String[] args) {
    Food food = Appetizer.SALAD;
    food = MainCourse.LASAGNE;
    food = Dessert.GELATO;
    food = Coffee.CAPPUCCINO;
  }
} ///:~
