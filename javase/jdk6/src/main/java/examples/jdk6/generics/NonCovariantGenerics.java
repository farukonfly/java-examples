package examples.jdk6.generics;
//: generics/NonCovariantGenerics.java
// {CompileTimeError} (Won't compile)
import java.util.ArrayList;
import java.util.List;

public class NonCovariantGenerics {
  // Compile Error: incompatible types:
 // List<Fruit> flist = new ArrayList<Apple>();
} ///:~
