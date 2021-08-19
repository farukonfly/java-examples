package examples.jdk6.generics;
//: generics/ExplicitTypeSpecification.java
import java.util.*;

import examples.jdk6.net.mindview.util.*;
import examples.jdk6.typeinfo.pets.*;

public class ExplicitTypeSpecification {
  static void f(Map<Person, List<Pet>> petPeople) {}
  public static void main(String[] args) {
    f(New.<Person, List<Pet>>map());
  }
} ///:~
