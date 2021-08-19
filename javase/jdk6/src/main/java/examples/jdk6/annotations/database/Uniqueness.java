//: annotations/database/Uniqueness.java
// Sample of nested annotations
package examples.jdk6.annotations.database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
