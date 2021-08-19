package examples.jdk6.gui;
//: gui/FlowLayout1.java
// Demonstrates FlowLayout.
import javax.swing.*;

import static examples.jdk6.net.mindview.util.SwingConsole.*;

import java.awt.*;

public class FlowLayout1 extends JFrame {
  public FlowLayout1() {
    setLayout(new FlowLayout());
    for(int i = 0; i < 20; i++)
      add(new JButton("Button " + i));
  }
  public static void main(String[] args) {
    run(new FlowLayout1(), 300, 300);
  }
} ///:~
