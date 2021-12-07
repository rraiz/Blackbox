import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Panel00 extends JPanel implements ActionListener
{
   private JPanel westPanel;
   private JPanel eastPanel; 
   
   private JButton[][] nButton;
   private JButton reset;
   private JLabel l1, l2, l3, l4, l5, l6, l7;
   private Blackbox game;
   
   public Panel00() throws IOException
   {
      game = new Blackbox();
   
   
      westPanel = new JPanel(); //west main panel
      eastPanel = new JPanel(); //east main panel
      
      add(westPanel, BorderLayout.WEST);
      add(eastPanel, BorderLayout.EAST); //adds both
      
      westPanel.setLayout(new GridLayout(12,12)); //makes the game boardx
      westPanel.setPreferredSize(new Dimension(550, 550));
      nButton = new JButton[12][12];
      makeButtons(); //adds the button
      
      eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS)); //east side text  
      eastPanel.setPreferredSize(new Dimension(300, 550));
   
      l1 = new JLabel("Number of guesses remaining: " + game.getGuessCount());
      l2 = new JLabel("Number of shots remaining: " + game.getShootCount());
      l3 = new JLabel("Number of mirrors remaining: " + game.getMirrorsLeft());
      l4 = new JLabel(" ");
      l5 = new JLabel(" ");
      l6 = new JLabel("Click on a letter to shoot a laser");
      l7 = new JLabel("Click on a black square to guess a mirror's location");
      
      reset = new JButton("Reset");
      
      eastPanel.add(Box.createRigidArea(new Dimension(0, 80)));
      eastPanel.add(l1);
      eastPanel.add(Box.createRigidArea(new Dimension(0, 80)));
      eastPanel.add(l2);
      eastPanel.add(Box.createRigidArea(new Dimension(0, 80)));
      eastPanel.add(l3);
      eastPanel.add(Box.createRigidArea(new Dimension(0, 80)));
      eastPanel.add(l4);
      eastPanel.add(l5);
      eastPanel.add(Box.createRigidArea(new Dimension(0, 50)));
      eastPanel.add(l6); 
      eastPanel.add(l7);     
      eastPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      eastPanel.add(reset);  
      reset.addActionListener(this);       
   }
   
   
   public void resetTheGame() 
   {
      try
      {
         Driver00.reset();
      }
      catch(Exception e)
      {
         return;
      }
      
   }

   
   public void actionPerformed(ActionEvent e) 
   {
      if(e.getSource() == reset)
      {
         resetTheGame();
         return;
      }
      
      if(game.getMirrorsLeft() == 0)
      {
         l6.setText("You found all the mirrors! You won!");
         l7.setText(" ");
         return;
      }
   
      if(game.getGuessCount() == 0)
      {
      
         l6.setText("Ran out of Guesses! You Lose!");
         l7.setText(" ");
         return;
      }
      
      int row = 0;
      int col = 0;
   
      for(int r = 0; r < nButton.length; r++)
      {
         for( int c = 0; c<nButton[0].length; c++)
         {
            if(e.getSource() == nButton[r][c])
            {
               row = r;
               col = c;
               break;
            }
         }
      }
      
      if(nButton[row][col].getText().equals("*"))
         return;
     
      if(row == 0 || row == nButton.length-1 || col == 0 || col == nButton[0].length-1 ) // top row
      {
         if(game.getShootCount() == 0)
         {
            l4.setText("Out of shots!");
            l5.setText(" ");
         }
         else
         {
            String[] info = game.shoot(nButton[row][col].getText().charAt(0));
            game.decreaseShootCount();
            l2.setText("Number of shots remaining: " + game.getShootCount());
            l4.setText("Landed at: " + info[0]);
            l5.setText("Bounced: " + info[1] + " time(s)");
         }
      }
               
      else //inside
      { 
         if(game.guess(row,col))
         {
            if(nButton[row][col].getBackground() == (Color.green))
            {
            }
            else
            {
               nButton[row][col].setText(game.getChar(row,col));
               nButton[row][col].setBackground(Color.green);
               nButton[row][col].setForeground(Color.black);
               game.decreaseMirrorsLeft();
               game.decreaseGuessCount();
               l3.setText("Number of mirrors remaining: " + game.getMirrorsLeft());
               l1.setText("Number of guesses remaining: " + game.getGuessCount());
            }
         }
         else
         {
            if(nButton[row][col].getBackground() == (Color.red))
            {
            }
            else
            {
               nButton[row][col].setBackground(Color.red);
               game.decreaseGuessCount();
               l1.setText("Number of guesses remaining: " + game.getGuessCount());
            }
         } 
      }
      
      if(game.getMirrorsLeft() == 0)
      {
         l6.setText("You found all the mirrors! You won!");
         l7.setText(" ");
         return;
      }
      
      if(game.getGuessCount() == 0)
      {
         for (int r = 1; r < nButton.length-1; r++)
         {
            for (int c = 1; c < nButton[0].length-1; c++)
            {
               if((game.getChar(r,c).equals("\\") || game.getChar(r,c).equals("/")) && (nButton[r][c].getBackground() != (Color.green)))
               {
                  nButton[r][c].setBackground(Color.pink);
                  nButton[r][c].setForeground(Color.black);  
                  nButton[r][c].setText(game.getChar(r,c)); 
               }
            }
         }
         l6.setText("Ran out of Guesses! You Lose!");
         l7.setText(" ");
         return;
      } 
   }
   
   
   public void makeButtons() throws IOException
   {    
      Scanner input = new Scanner(new FileReader("board.txt"));
      
      for (int r = 0; r < nButton.length; r++)
      {
         for (int c = 0; c < nButton[0].length; c++)
         {
            String temp = input.nextLine();
            if(temp.equals("."))
            {
               nButton[r][c] = new JButton("");
               nButton[r][c].setBackground(Color.black);
               nButton[r][c].setForeground(Color.white);
               nButton[r][c].addActionListener(this);
            }
            else
            {
               nButton[r][c] = new JButton(temp);
               nButton[r][c].addActionListener(this);
            }
            westPanel.add(nButton[r][c]);
         }
      }
      input.close();
   }
   
}