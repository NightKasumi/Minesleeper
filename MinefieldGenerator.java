import java.lang.Math. *;
public class MinefieldGenerator extends Main{
    private int size = 0;
    int warn = 0;
    String[][] playerField = new String[20][20];
    
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
    public String checkForBombs(int x, int y){
/*
        warn = 0;
        for (int i = x+2; i >= x; i--){
           for (int c = y-2; c <= y; c++){
               if (playerField[i][c].contains("💣")){
                   warn++;
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

   return playerField[y][x];
    */
    for (int i = 0; i < 20; i++){
            for (int c = 0; c < 20; c++){
                    if(playerField[i][c].equals("💣"))
                                for (int b = i-1; b >= i+1; b--){
                                    for (int n = c+1; n <= c-1; n++){
    try {
 if(playerField[n][b].equals("⬜")){
     playerField[n][b] = ".";
     }else{
         playerfield[n][b] += ".";
     }
}catch(Exception ArrayIndexOutOfBounds) {
continue;

                                        
                                        
                                    }
                                }
            
        
    return "!!!";    
    }
    
    public String[][] getWholeArray(){
        return playerField;
    }
}
