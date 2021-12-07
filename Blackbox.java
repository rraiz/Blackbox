import java.util.*;
import java.io.*;

public class Blackbox 
{
   private char[][] userList;
   private char[][] answerList;
   private int mirrorsLeft;
   private int shootCount;
   private int guessCount;

   public Blackbox() throws IOException
   {
      answerList = createArray();
      userList = createArray();
      answerList = addMirrors(answerList);
      mirrorsLeft = 10;
      shootCount = 25;
      guessCount = 20;
   }
   
      
   public int getMirrorsLeft()
   {
      return mirrorsLeft;
   }

   public int getShootCount()
   {
      return shootCount;
   }

   public int getGuessCount()
   {
      return guessCount;
   }


   public void decreaseMirrorsLeft()
   {
      mirrorsLeft--;
   }

   public void decreaseShootCount()
   {
      shootCount--;
   }

   public void decreaseGuessCount()
   {
      guessCount--;
   }


   public String getChar(int row, int col)
   {
      String temp = "";
      return temp + answerList[row][col]; 
   }

   public boolean guess(int row, int col)
   {
      if(answerList[row][col] == '/' || answerList[row][col] == '\\')
      {
         if(answerList[row][col] == '/')
            userList[row][col] = '/';
         else
            userList[row][col] = '\\';
         return true;
      }  
      else
         return false;
   } 

   public String[] shoot(char station)
   {
      Laser weapon = new Laser(station);
      
      String[] arr = new String[2];
      int bounced = 0;
      
      while(!(weapon.getRow() == 0 || weapon.getRow() == 11 || weapon.getCol() == 0 || weapon.getCol() ==11))
      {  
         if(answerList[weapon.getRow()][weapon.getCol()] == '/' && weapon.getDir() == Laser.DOWN) //starting from top /
         {
            weapon.setDir(Laser.LEFT);
            bounced++;
         }
         else if(answerList[weapon.getRow()][weapon.getCol()] == '\\' && weapon.getDir() == Laser.DOWN) //starting from top \
         {
            weapon.setDir(Laser.RIGHT);
            bounced++;
         }
         
         
         else if(answerList[weapon.getRow()][weapon.getCol()] == '/' && weapon.getDir() == Laser.UP) //starting from bottom /
         {
            weapon.setDir(Laser.RIGHT);
            bounced++;
         }
         else if(answerList[weapon.getRow()][weapon.getCol()] == '\\' && weapon.getDir() == Laser.UP) //starting from bottom  \
         {
            weapon.setDir(Laser.LEFT);
            bounced++;
         }
         
         
         else if(answerList[weapon.getRow()][weapon.getCol()] == '/' && weapon.getDir() == Laser.RIGHT) //starting from left /
         {
            weapon.setDir(Laser.UP);
            bounced++;
         }
         else if(answerList[weapon.getRow()][weapon.getCol()] == '\\' && weapon.getDir() == Laser.RIGHT) //starting from left \
         {
            weapon.setDir(Laser.DOWN);
            bounced++;
         }
         
         
         else if(answerList[weapon.getRow()][weapon.getCol()] == '/' && weapon.getDir() == Laser.LEFT) //starting from right /
         {
            weapon.setDir(Laser.DOWN);
            bounced++;
         }
         else if(answerList[weapon.getRow()][weapon.getCol()] == '\\' && weapon.getDir() == Laser.LEFT) //starting from right /
         {
            weapon.setDir(Laser.UP);
            bounced++;
         }
         
         weapon.move();  
      }
      
      String temp = "";
      
      arr[0] = temp + (answerList[weapon.getRow()][weapon.getCol()]);
      arr[1] = temp + bounced;
      
      return arr;
   
   }


   private char[][] addMirrors(char[][] answerList)
   {
      int x = 0;
      while(x != 10)
      {
         int r = (int)(Math.random() *2);
         if( r == 0)
         {
            int rand = (int)(Math.random() *100) + 1;
            int ones = rand/10+1;
            int tens = rand%10+1;
            if(rand == 100)
               ones = 1;
            if(answerList[tens][ones] == '/' || answerList[tens][ones] == '\\')
               continue; 
          
            answerList[tens][ones] = '/'; 
            x++;
         }
         else
         {
            int rand = (int)(Math.random() *100) + 1;
            int ones = rand/10+1;
            int tens = rand%10+1; 
            if(rand == 100)
               ones = 1;
            if(answerList[tens][ones] == '/' || answerList[tens][ones] == '\\')
               continue; 
               
            answerList[tens][ones] = '\\';   
            x++;
         }
      } 
      return answerList;
   }
   
   private char[][] createArray() throws IOException
   {
      char[][] list = new char[12][12];
      
      Scanner input = new Scanner(new FileReader("board.txt"));
   
      
      for(int r = 0; r<list.length; r++)
      {
         for(int c = 0; c<list[0].length; c++)
         { 
            list[r][c] = input.nextLine().charAt(0);
         }
      }
     
      input.close();
      return list;
   }
        
      
}

