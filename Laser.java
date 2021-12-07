public class Laser
{
   private int row, col, dir;  
   public static final int UP    = 0; //ClassWithStaticVariable.VariableName
   public static final int RIGHT = 1;
   public static final int DOWN  = 2;
   public static final int LEFT  = 3;

   public Laser (char station)
   {
      if(station>='a' && station<='j')
      {
         row = 1;
         col = (int)(station-'a')+1;
         dir = DOWN;
      }
      else if(station>='A' && station<='J')
      {
         row = 10;
         col = (int)(station-'A')+1;
         dir = UP; 
      }
      else if(station>='k' && station<='t')
      {
         row = (int)(station-'k')+1;
         col = 1;
         dir = RIGHT;  
      }
      else   //if(station>='K' && station<='T')
      {
         row = (int)(station-'K')+1;
         col = 10;
         dir = LEFT; 
      
      }        
   }

   public void move()
   {
      if(dir == UP)
      {
         row--;
      }
      if(dir == RIGHT)
      {
         col++;
      }
      if(dir == DOWN)
      {
         row++;
      }
      if(dir == LEFT)
      {
         col--;
      }
   }

   public int getDir()
   {
      return dir;
   }
   
   public void setDir(int d)
   {
      dir = d;
   }
   
   public int getRow()
   {
      return row;
   }
   public int getCol()
   {
      return col;
   }
      

//other Laser methods defined here
}