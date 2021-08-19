package examples.jdk6.generics;
//: generics/LimitsOfInference.java
import java.util.*;

import examples.jdk6.typeinfo.pets.*;

public class LimitsOfInference {
  static void
  f(Map<Person, List<? extends Pet>> petPeople) {}
  public static void main(String[] args) {
    // f(New.map()); // Does not compile
  }
} ///:~
