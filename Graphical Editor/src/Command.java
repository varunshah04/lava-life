import java.awt.*;
/**
 * Command.java
 * Superclass for commands.
 * Provides empty definitions for the methods executeClick, executePress,
 * and executeDrag.
 * 
 * Written by THC for CS 5 Lab Assigment 3.
 * 
 * @author Thomas H. Cormen
 */
public class Command {
  public void executeClick(Point p, Drawing dwg) { }
  public void executePress(Point p, Drawing dwg) { }
  public void executeDrag(Point p, Drawing dwg) { }
}