package examples.jdk6.gui;
//: gui/BorderLayout1.java
// Demonstrates BorderLayout.
import javax.swing.*;

import static examples.jdk6.net.mindview.util.SwingConsole.*;

import java.awt.*;

public class BorderLayout1 extends JFrame {
  public BorderLayout1() {
    add(BorderLayout.NORTH, new JButton("North"));
    add(BorderLayout.SOUTH, new JButton("South"));
    add(BorderLayout.EAST, new JButton("East"));
    add(BorderLayout.WEST, new JButton("West"));
    add(BorderLayout.CENTER, new JButton("Center"));
  }
  public static void main(String[] args) {
    run(new BorderLayout1(), 300, 250);
  }
} ///:~
