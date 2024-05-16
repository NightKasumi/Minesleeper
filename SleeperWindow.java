import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SleeperWindow extends Main {

    private class Tiles extends JButton {
        int r;
        int c;

        public Tiles(int r, int c) {
            this.r = r;
            this.c = c;

        }

    }

    int tileSize = 32;
    int numRows = super.getRows();
    int numCols = numRows;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;
    boolean gameOver = false;
    int mines = MinefieldGenerator.getMines();
    int flagCount = mines;
    int correctFlags = 0;

    JFrame frame = new JFrame("Minesleeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    ArrayList<String> mineList = MinefieldGenerator.getMineList();

    String[][] playerList = MinefieldGenerator.getWholeArray();

    Tiles[][] board = new Tiles[numRows][numCols];

    SleeperWindow() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 24));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesleeper | Flags: " + flagCount);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r=0;r<numRows;r++) {
            for (int c=0;c<numCols;c++) {
                Tiles tile = new Tiles(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                // tile.setText(" ");
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Tiles tile = (Tiles) e.getSource();
                        // Left Click
                        if (gameOver == false) {
                            if (e.getButton() == MouseEvent.BUTTON1) {
                                if (tile.getText() == "") {
                                    if (MinefieldGenerator.checkForBombs(tile.r, tile.c).contains("💣")) {
                                        gameOver = true;
                                        textLabel.setText("Game Over!");
                                        tile.setText("💣");
                                    } else {
                                        tile.setEnabled(false);
                                        floodFill(tile.r, tile.c);
                                        if (!MinefieldGenerator.checkForBombs(tile.r, tile.c).contains("0")) {
                                            tile.setText(MinefieldGenerator.checkForBombs(tile.r, tile.c));
                                        }
                                    }
                                }
                            }

                            if (e.getButton() == MouseEvent.BUTTON3) {
                                if (tile.getText() == "" && flagCount > 0) {
                                    tile.setText("⚑");
                                    flagCount--;
                                    textLabel.setText("Minesleeper | Flags: " + flagCount);
                                    if (mineList.contains(tile.r + "," + tile.c)) {
                                        correctFlags++;
                                    }
                                    if (correctFlags == mines) {
                                        textLabel.setText("You win, but you get nothing!!!");
                                        gameOver = true;
                                    }
                                } else if (tile.getText() == "⚑") {
                                    tile.setText("");
                                    flagCount++;
                                    textLabel.setText("Minesleeper | Flags: " + flagCount);
                                    if (mineList.contains(tile.r + "," + tile.c)) {
                                        correctFlags--;
                                    }
                                }
                                System.out.println("correctFlags = " + correctFlags);
                                System.out.println("mines = " + mines);
                            }
                        }
                    }
                });

                boardPanel.add(tile);

            }
        }
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
    }

    public void floodFill (int x, int y) {
        if (x<board.length && x>0 && y<board[x].length && y>0) {
            Tiles tile = board[x][y];
            if (MinefieldGenerator.checkForBombs(x, y).contains("0")) {
                tile.setEnabled(false);
                floodFill(x+1, y);
                floodFill(x, y+1);
                floodFill(x, y-1);
                floodFill(x-1, y);
            }
        }
    } 
}
