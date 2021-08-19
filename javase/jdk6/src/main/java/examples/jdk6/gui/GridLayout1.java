package examples.jdk6.gui;
//: gui/GridLayout1.java
// Demonstrates GridLayout.
import javax.swing.*;

import static examples.jdk6.net.mindview.util.SwingConsole.*;

import java.awt.*;

public class GridLayout1 extends JFrame {
  public GridLayout1() {
    setLayout(new GridLayout(7,3));
    for(int i = 0; i < 20; i++)
      add(new JButton("Button " + i));
  }
  public static void main(String[] args) {
    run(new GridLayout1(), 300, 300);
  }
} ///:~
