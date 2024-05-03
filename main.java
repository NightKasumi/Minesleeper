import java.util.Scanner;
public class Main
{

    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int blownUp = 0;

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Your goal is to mark the spots that conatin a bomb with a flag, which can be found by numbers letting you know how close you are to them. If you select a bomb spot without a flag, you're out!");
        MinefieldGenerator yes = new MinefieldGenerator(20);
        yes.genMinefield(20);
        String[][] playerside = new String[20][20];
        
;

        for(int c = 0; c < 20; c++){
            for(int v = 0; v<20;v++){
                            playerside[v][c] = "⬜";
                            
                        }
                    }
        //playerside = yes.getWholeArray();
        System.out.println("what player sees this turn");
        for(int v = 0; v<20; v++){
             for (int b = 0; b<20; b++){
                 System.out.print(playerside[v][b] + " ");
             }
            System.out.println();
         }
        
    // C - this is a tester for returning a specified place value
        System.out.println(yes.returnPlace(3,4));   
        while(blownUp == 0){
        System.out.println("Pick a X coordinate to select.");
        int sel1 = input.nextInt();
        while (sel1 > 20 || sel1 < 0) {
            System.out.println("You're an idiot.");
            System.out.println("Pick a better X coordinate to select.");
            sel1 = input.nextInt();
        }
        System.out.println("Pick a Y coordinate to select.");
        int sel2 = input.nextInt();
        while (sel2 > 20 || sel2 < 0) {
            System.out.println("You're an idiot.");
            System.out.println("Pick a better Y coordinate to select.");
            sel2 = input.nextInt();
        }
        playerside[sel1][sel2] = yes.checkForBombs(sel1,sel2);
        if(yes.checkForBombs(sel1,sel2).equals("💣")){
            System.out.println("You found a Mine! You lose!!! get better tbh");
            break;
        }
       System.out.println("what player sees this turn");
        for(int v = 0; v<20; v++){
             for (int b = 0; b<20; b++){
                 System.out.print(playerside[v][b] + " ");
             }
            System.out.println();
         } 
    }
    }
    
    //public void 


    
    
} 
