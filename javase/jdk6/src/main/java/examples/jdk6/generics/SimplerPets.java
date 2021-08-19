package examples.jdk6.generics;
//: generics/SimplerPets.java
import java.util.*;

import examples.jdk6.net.mindview.util.*;
import examples.jdk6.typeinfo.pets.*;

public class SimplerPets {
  public static void main(String[] args) {
    Map<Person, List<? extends Pet>> petPeople = New.map();
    // Rest of the code is the same...
  }
} ///:~
