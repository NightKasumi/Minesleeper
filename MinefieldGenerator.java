import java.lang.Math.*;
public class MinefieldGenerator extends Main{
    private int size = 0;
    static int warn = 0;

    static int boardRows = Main.getRows();

    static String[][] playerField = new String[boardRows][boardRows];
    
    public MinefieldGenerator(int size) {
    this.size = size; 
    
    
    }

        public void genMinefield(int leng){

         int mines = 0;
            MinefieldGenerator minefield = new MinefieldGenerator(leng);
            String[][] mine = new String[leng][leng];
            for(int i = 0; i < leng; i++){
                for(int c = 0; c < leng; c++){
                    if (((double)Math.random()*8 + 1/2) > 7){
                        mine[i][c] = "💣";
                        }else{
                            mine[i][c] = "⬜";
                            
                        }
                    }
        playerField = mine;   
            }
         for(int v = 0; v<leng; v++){
             for (int b = 0; b<leng; b++){
                 if(mine[v][b].equals("💣")){
                     mines++;
                 }
                 System.out.print(mine[v][b] + " ");
             }
            System.out.println();
         }   
        System.out.println("number of mines: " + mines);
            
        }
    public String returnPlace(int x, int y){
        return playerField[y][x];
    }
    public static String checkForBombs(int x, int y){
        warn = 0;
        for (int i = x+2; i >= x; i--){
           for (int c = y-2; c <= y; c++){
               if (playerField[y][x].contains("💣")){
                    SleeperWindow.revealMines(playerField);
                    return "💣";
               }
               if (playerField[i][c].contains("💣")){
                   warn++;
               }
    
               
           }
    }
    playerField[y][x] = String.valueOf(warn);
    warn = 0;
     for (int i = x; i >= x+2; i--){
           for (int c = y; c <= y-2; c++){
      if((i!=x) && (c!=y) && (x <= 19) && (x >= 1) && (y >= 1) && (y <= 19)){
      checkForBombs(i,c);
      }
     }
    }

   return " " + playerField[y][x];
    }
    public static String[][] getWholeArray(){
        return playerField;
    }
}
