import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
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

    JFrame frame = new JFrame("Minesleeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    ArrayList<Tiles> mineList = new ArrayList<Tiles>();

    String[][] playerList = MinefieldGenerator.getWholeArray();
/*
    for (int i=0;i<playerList.length;i++) {
        for (int c=0;c<playerList[i].length;c++) {
            if (playerList[i][c].contains("💣")) {
                mineList.add(playerList[i][c]);
            }
        }
    }
*/
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
        textLabel.setText("Minesleeper");
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
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (tile.getText() == "") {
                                if (MinefieldGenerator.checkForBombs(tile.c, tile.r).equals("💣")) {
                                    tile.setText("💣");
                                    textLabel.setText("Game Over!");
                                } else {
                                    MinefieldGenerator.checkForBombs(tile.r, tile.c);
                                }
                            }
                        }
                    }
                });

                boardPanel.add(tile);

            }
        }
        frame.setVisible(true);
    }
}