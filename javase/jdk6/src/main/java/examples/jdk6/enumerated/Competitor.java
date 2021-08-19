//: enumerated/Competitor.java
// Switching one enum on another.
package examples.jdk6.enumerated;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
