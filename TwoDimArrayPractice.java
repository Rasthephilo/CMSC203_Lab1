package twodarray;

/* TwoDimArrayPractice
*  Students Work with this Java file
*  On Multi-dimensional Arrays
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.util.*;

public class TwoDimArrayPractice extends JFrame {
   private JButton fillValues, printArray, setValues, findMinimum, countFrequency;
   private ButtonHandler bh;

   private static int [][] intArray;
   private final int ROWS = 4;
   private final int COLUMNS = 20;
   private static int current1 = -1;
   private static int current2 = -1;
   private int key;
   private int rowSelected = -1;
   private int columnSelected = -1;
   private BarChart bc;
   private static int counter = 0;

   private static TwoDimArrayPractice app;
   private boolean firstTime = true;

   private Image offscreen;

   public TwoDimArrayPractice( ) {
      super("Choose your activity");
      Container c = getContentPane( );
      c.setLayout(new FlowLayout());

      fillValues = new JButton("Fill Values");
      c.add(fillValues);
      printArray = new JButton("Print Array");
      c.add(printArray);
      setValues = new JButton("Set Values");
      c.add(setValues);
      findMinimum = new JButton("Find Minimum");
      c.add(findMinimum);
      countFrequency = new JButton("Count Frequency");
      c.add(countFrequency);

      bh = new ButtonHandler();
      fillValues.addActionListener(bh);
      printArray.addActionListener(bh);
      setValues.addActionListener(bh);
      findMinimum.addActionListener(bh);
      countFrequency.addActionListener(bh);

      setSize(500, 550);

      intArray = new int[ROWS][COLUMNS];
      Random rand = new Random();
      for (int i = 0; i < intArray.length; i++) {
         for (int j = 0; j < intArray[0].length; j++) {
            intArray[i][j] = rand.nextInt(31) + 50;
         }
      }

      bc = new BarChart(intArray);

      System.out.println("Row\tValue");
      for (int i = 0; i < intArray.length; i++) {
         System.out.print(i + "\t");
         for (int j = 0; j < intArray[i].length; j++) {
            System.out.print(intArray[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println();

      setVisible(true);
      offscreen = this.createImage(getSize().width, getSize().height);
   }

   public void fillValues() {
      Random rand = new Random();
      for (int row = 0; row < intArray.length; row++) {
         System.out.print(row + "\t");
         for (int column = 0; column < intArray[row].length; column++) {
            intArray[row][column] = rand.nextInt(31) + 50;
            animate(row, column);
         }
         System.out.println();
      }
   }

   public void printArray() {
      for (int row = 0; row < intArray.length; row++) {
         System.out.print(row + "\t");
         for (int column = 0; column < intArray[row].length; column++) {
            System.out.print(intArray[row][column] + " ");
            animate(row, column);
         }
         System.out.println();
      }
   }

   public void setValues(int value, int row) {
      for (int column = 0; column < intArray[row].length; column++) {
         intArray[row][column] = value;
         animate(row, column);
      }
   }

   public int findMinimum(int column) {
      int min = intArray[0][column];
      for (int row = 0; row < intArray.length; row++) {
         if (intArray[row][column] < min) {
            min = intArray[row][column];
         }
         animate(row, column, min);
      }
      return min;
   }

   public int countFound(int value) {
      int count = 0;
      for (int row = 0; row < intArray.length; row++) {
         for (int column = 0; column < intArray[row].length; column++) {
            if (intArray[row][column] == value) {
               count++;
            }
            animate(row, column, count);
         }
      }
      return count;
   }

   public void startActivity(int act) {
      bc.setActivity(act);
      boolean goodInput = false;
      String answer = "";

      switch (act) {
         case 0: fillValues(); JOptionPane.showMessageDialog(null, "Array filled with new values"); break;
         case 1: printArray(); JOptionPane.showMessageDialog(null, "Array printed"); break;
         case 2:
            while (!goodInput || key < 50 || key > 80) {
               try {
                  answer = JOptionPane.showInputDialog(null, "Enter a value between 50 and 80");
                  if (answer != null) {
                     key = Integer.parseInt(answer);
                     goodInput = true;
                  } else break;
               } catch (Exception e) {}
            }
            if (goodInput) {
               goodInput = false;
               while (!goodInput || rowSelected < 0 || rowSelected > 3) {
                  try {
                     answer = JOptionPane.showInputDialog(null, "Enter a row number between 0 and 3");
                     if (answer != null) {
                        rowSelected = Integer.parseInt(answer);
                        goodInput = true;
                     } else break;
                  } catch (Exception e) {}
               }
            }
            if (goodInput) {
               bc.setKey(key);
               setValues(key, rowSelected);
               String message = bc.getCheckNewValues() ? " correctly" : " incorrectly";
               JOptionPane.showMessageDialog(null, "Values in row " + rowSelected + " set to " + key + message);
            }
            break;
         case 3:
            while (!goodInput || columnSelected < 0 || columnSelected > 19) {
               try {
                  answer = JOptionPane.showInputDialog(null, "Enter a column number between 0 and 19");
                  if (answer != null) {
                     columnSelected = Integer.parseInt(answer);
                     goodInput = true;
                  } else break;
               } catch (Exception e) {}
            }
            if (goodInput) {
               int a = findMinimum(columnSelected);
               String feedbackMin = (a == bc.getExactMinimum()) ? "\nThis is correct" : "\nThis is incorrect";
               JOptionPane.showMessageDialog(null, "In column " + columnSelected + ", you found a minimum value of " + a + feedbackMin);
            }
            break;
         case 4:
            while (!goodInput || key < 50 || key > 80) {
               try {
                  answer = JOptionPane.showInputDialog(null, "Enter a value between 50 and 80");
                  if (answer != null) {
                     key = Integer.parseInt(answer);
                     goodInput = true;
                  } else break;
               } catch (Exception e) {}
            }
            if (goodInput) {
               int frequency = countFound(key);
               String feedbackFrequency = (frequency == bc.getExactFrequencyCount()) ? "\nThis is correct" : "\nThis is incorrect";
               String plural = (frequency != 1) ? "s" : "";
               String displayMessageFrequency = "You found " + key + " " + frequency + " time" + plural + feedbackFrequency;
               if (frequency != -1)
                  JOptionPane.showMessageDialog(null, displayMessageFrequency);
               else
                  JOptionPane.showMessageDialog(null, "You did not find the value " + key);
            }
            break;
      }
      enableButtons();
   }

   public static int getCurrent1() { return current1; }
   public static int getCurrent2() { return current2; }
   public static int getCounter() { return counter; }
   public static int [][] getArray() { return intArray; }

   private void animate(int row, int column) {
      if (bc.getActivity() >= 0 && bc.getActivity() <= 2) {
         try {
            current1 = row; current2 = column;
            bc.setArray(intArray);
            Graphics g = offscreen.getGraphics();
            paint(g);
            g = this.getGraphics();
            g.drawImage(offscreen, 0, 0, this);
            Thread.sleep(bc.getActivity() == 0 ? 200 : 500);
         } catch (InterruptedException e) {}
      } else {
         JOptionPane.showMessageDialog(null, "Wrong number of arguments to animate method");
         System.exit(1);
      }
   }

   private void animate(int row, int column, int intermedResult) {
      if (bc.getActivity() == 3 || bc.getActivity() == 4) {
         try {
            current1 = row; current2 = column;
            bc.setStudentResult(intermedResult);
            bc.setArray(intArray);
            Graphics g = offscreen.getGraphics();
            paint(g);
            g = this.getGraphics();
            g.drawImage(offscreen, 0, 0, this);
            Thread.sleep(500);
         } catch (InterruptedException e) {}
      } else {
         JOptionPane.showMessageDialog(null, "Wrong number of arguments to animate method");
         System.exit(1);
      }
   }

   public void paint(Graphics g) {
      if ((current1 != -1 && current2 != -1) || firstTime) {
         super.paint(g);
         bc.draw(g);
         bc.updateBarChart(key, current1, current2, g);
         firstTime = false;
      }
   }

   public static void main(String[] args) {
      app = new TwoDimArrayPractice();
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void disableButtons() {
      fillValues.setEnabled(false);
      printArray.setEnabled(false);
      setValues.setEnabled(false);
      countFrequency.setEnabled(false);
      findMinimum.setEnabled(false);
   }

   public void enableButtons() {
      fillValues.setEnabled(true);
      printArray.setEnabled(true);
      setValues.setEnabled(true);
      countFrequency.setEnabled(true);
      findMinimum.setEnabled(true);
   }

   private class ButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         PrintArrayT t = new PrintArrayT(app);
         if (e.getSource() == fillValues) {
            disableButtons(); fillValues.requestFocus(); bc.setActivity(0); t.start();
         } else if (e.getSource() == printArray) {
            disableButtons(); printArray.requestFocus(); bc.setActivity(1); t.start();
         } else if (e.getSource() == setValues) {
            disableButtons(); setValues.requestFocus(); bc.setActivity(2); t.start();
         } else if (e.getSource() == findMinimum) {
            disableButtons(); findMinimum.requestFocus(); bc.setActivity(3); t.start();
         } else if (e.getSource() == countFrequency) {
            disableButtons(); countFrequency.requestFocus(); bc.setActivity(4); t.start();
         }
      }
   }

   public void resetButtonSelection() {
      fillValues.setSelected(false);
      printArray.setSelected(false);
      setValues.setSelected(false);
      findMinimum.setSelected(false);
      countFrequency.setSelected(false);
   }

   private class PrintArrayT extends Thread {
      int [][] arr;
      TwoDimArrayPractice s1;
      public PrintArrayT (TwoDimArrayPractice s) {
         arr = TwoDimArrayPractice.intArray;
         s1 = s;
      }
      public void run() {
         startActivity(bc.getActivity());
         enableButtons();
      }
   }
}
