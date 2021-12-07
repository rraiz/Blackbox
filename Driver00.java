import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Driver00
{

   public static JFrame frame;
   
   public static void main(String [] args) throws IOException
   {
   
      frame = new JFrame("Blackbox");
      frame.setSize(950, 600);
      frame.setLocation(200, 200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new Panel00());
      frame.setVisible(true);
      
   
   
   }
   public static void reset() throws IOException
   {
      frame.setContentPane(new Panel00());
      frame.setVisible(true);
   }
}