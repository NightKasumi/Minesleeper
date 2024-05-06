import java.util.Scanner;

import javax.swing.*;


public class Main
{

    static int boardRows = 0;

    
    public static void main(String[] args)
    {

        

        Scanner input = new Scanner(System.in);
        int blownUp = 0;

        boardRows = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount of rows on the board"));

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Your goal is to mark the spots that conatin a bomb with a flag, which can be found by numbers letting you know how close you are to them. If you select a bomb spot without a flag, you're out!");
        MinefieldGenerator yes = new MinefieldGenerator(boardRows);
        yes.genMinefield(boardRows);
        String[][] playerside = new String[boardRows][boardRows];
        
        SleeperWindow gui = new SleeperWindow();

        for(int c = 0; c < boardRows; c++){
            for(int v = 0; v<boardRows;v++){
                            playerside[v][c] = "⬜";
                            
                        }
                    }
        //playerside = yes.getWholeArray();
        System.out.println("what player sees this turn");
        for(int v = 0; v<boardRows; v++){
             for (int b = 0; b<boardRows; b++){
                 System.out.print(playerside[v][b]);
             }
            System.out.println();
         }
        
    // C - this is a tester for returning a specified place value
        System.out.println(yes.returnPlace(3,4));   
        while(blownUp == 0){
        System.out.println("Pick a X coordinate to select.");
        int sel2 = input.nextInt();
        while (sel2 > boardRows || sel2 < 0) {
            System.out.println("You're an idiot.");
            System.out.println("Pick a better X coordinate to select.");
            sel2 = input.nextInt();
        }
        System.out.println("Pick a Y coordinate to select.");
        int sel1 = input.nextInt();
        while (sel1 > boardRows || sel1 < 0) {
            System.out.println("You're an idiot.");
            System.out.println("Pick a better Y coordinate to select.");
            sel1 = input.nextInt();
        }
        playerside[sel1][sel2] = MinefieldGenerator.checkForBombs(sel1,sel2);
        
       System.out.println("what player sees this turn");
        for(int v = 0; v<boardRows; v++){
             for (int b = 0; b<boardRows; b++){
                 System.out.print(playerside[v][b] + " ");
             }
            System.out.println();
         } 
    if(MinefieldGenerator.checkForBombs(sel1,sel2).equals("💣")){
            System.out.println("You found a Mine! You lose!!! get better tbh");
            break;
        }
            
        }

        
        

    }

    public static int getRows() {
        return boardRows;
    }

    
    
    //public void 


    
    
}
