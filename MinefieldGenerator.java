import java.util.ArrayList;

public class MinefieldGenerator extends Main{
    private int size = 0;
    static int warn = 0;
    static int mines;
    static int danger;

    static int boardRows = Main.getRows();

    static String[][] playerField = new String[boardRows][boardRows];

    static int[][] numField = new int[boardRows][boardRows];
    
    public MinefieldGenerator(int size) {
    this.size = size; 
    
    
    }

        public void genMinefield(int leng){

        mines = 0;
            MinefieldGenerator minefield = new MinefieldGenerator(leng);
            String[][] mine = new String[leng][leng];
            for(int i = 0; i < leng; i++){
                for(int c = 0; c < leng; c++){
                    if (((double)Math.random()*8 + 1/2) > 7.5){
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
                 System.out.print(mine[v][b]);
             }
            System.out.println("");
        }
        System.out.println("number of mines: " + mines);
        
    }

        public static int getMines() {
            return mines;
        }

    public String returnPlace(int x, int y){
        return playerField[x][y];
    }

    public static ArrayList<String> getMineList() {
        ArrayList<String> mineList = new ArrayList<String>();

        for (int i=0;i<playerField.length;i++) {
            for (int c=0;c<playerField[i].length;c++) {
                if (playerField[i][c].contains("💣")) {
                    mineList.add(i + ", " + c);
                }
            }
        }

        return mineList;
    }

    public static String checkForBombs(int x, int y) {
        int warn = 0;
        ArrayList<String> arr = getMineList();

        if (playerField[x][y].contains("💣")) {
            return "💣";
        } else {
            for(int i = x-1; i <= x+1; i++) {
                for(int j = y-1; j <= y+1; j++) {
                        //process tile (i,j)
                    if (arr.contains(i + ", " + j)) {
                        warn++;
                    }
                }
            }
        }
        playerField[x][y] = String.valueOf(warn);
        warn = 0;

        return (String) playerField[x][y];
    }

/*
    public static String checkForBombsTemp(int x, int y){
        warn = 0;

        for (int i = x+1; i >= x-1; i--){
            for (int c = y-1; c <= y-1; c++){
                if ((c<0 || c>playerField.length) || (i<0 || i>playerField.length)) {
                continue;
                } else {
                    if (playerField[x][y].contains("💣")){
                        return "💣";
                    }
                    if (playerField[i][c].contains("💣")){
                        warn++;
                    }
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

   return (String) playerField[y][x];
    }
*/

    

    public static String[][] getWholeArray(){
        return playerField;
    }
}
