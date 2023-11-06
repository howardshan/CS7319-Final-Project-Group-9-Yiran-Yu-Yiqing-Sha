package Unselected.Subroutine.MainGame;

import javax.swing.*;
import java.awt.*;

public class GameLogic {

    private GameUI gameUI;
    public GameLogic(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    private final int boardSize = 10;
    private int selectedX = -1, selectedY = -1;//unselected, illegal

    //Determine if the click is on the same point
    public void onButtonClicked(int x, int y) {
        System.out.println("Button clicked at: " + x + ", " + y);
        if (selectedX == -1) {
            selectedX = x;
            selectedY = y;
            gameUI.buttons[selectedX][selectedY].setOpaque(true); // Set the button to be opaque
            gameUI.buttons[selectedX][selectedY].setContentAreaFilled(true); // This will fill the content area
            gameUI.buttons[selectedX][selectedY].setBackground(Color.BLUE);
        } else {
            // Check if the selected tile is not the same as the previously selected tile
            if (selectedX != x || selectedY != y) {
                if (canConnect(selectedX, selectedY, x, y)) {
                    gameUI.board[selectedX][selectedY] = null;
                    gameUI.board[x][y] = null;
                    gameUI.buttons[selectedX][selectedY].setOpaque(true);
                    gameUI.buttons[selectedX][selectedY].setContentAreaFilled(true);
                    gameUI.buttons[selectedX][selectedY].setBackground(null);
                    gameUI.refreshUI();
                }
            }
            gameUI.buttons[selectedX][selectedY].setOpaque(true);
            gameUI.buttons[selectedX][selectedY].setContentAreaFilled(true);
            gameUI.buttons[selectedX][selectedY].setBackground(null);
            selectedX = -1;
            selectedY = -1;
        }
        if (isBoardEmpty()) {
            JOptionPane.showMessageDialog(gameUI, "You're WIN!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Check if the game is over
    private boolean isBoardEmpty() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (gameUI.board[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean canConnect(int x1, int y1, int x2, int y2) {
        GameUI.Tile tile1 = gameUI.board[x1][y1];
        GameUI.Tile tile2 = gameUI.board[x2][y2];
        System.out.println("A");

        if (tile1 == null || tile2 == null) {
            System.out.println("b");
            return false;
        }

        if ((tile1.imageId != null && tile2.number != null && tile1.imageId.equals(tile2.number))
                || (tile1.number != null && tile2.imageId != null && tile1.number.equals(tile2.imageId))) {

        } else {

            return false;
        }
        if ((x1 == x2 && (x1 == 0 || x1 == 4)) || (y1 == y2 && (y1 == 0 || y1 == 4))) {
            return true;
        }

        if (isDirectlyConnected(x1, y1, x2, y2)) {
            return true;
        }

        // Judging "one-fold connections"
        if (x1 == x2) {
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            boolean directlyConnected = true;
            for (int y = minY + 1; y < maxY; y++) {
                if (gameUI.board[x1][y] != null) {
                    directlyConnected = false;
                    break;
                }
            }
            if (directlyConnected) {
                return true;
            }

            boolean leftClear = true, rightClear = true;
            for (int y = minY; y <= maxY; y++) {
                if (x1 > 0 && gameUI.board[x1-1][y] != null) {
                    leftClear = false;
                }
                if (x1 < gameUI.board.length - 1 && gameUI.board[x1+1][y] != null) {
                    rightClear = false;
                }
            }
            if (leftClear || rightClear) {
                return true;
            }
        }

        // Check if two tiles are on the same line
        if (y1 == y2) {
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            boolean directlyConnected = true;
            for (int x = minX + 1; x < maxX; x++) {
                if (gameUI.board[x][y1] != null) {
                    directlyConnected = false;
                    break;
                }
            }
            if (directlyConnected) {
                return true;
            }

            boolean upClear = true, downClear = true;
            for (int x = minX; x <= maxX; x++) {
                if (y1 > 0 && gameUI.board[x][y1-1] != null) {
                    upClear = false;
                }
                if (y1 < gameUI.board[0].length - 1 && gameUI.board[x][y1+1] != null) {
                    downClear = false;
                }
            }
            if (upClear || downClear) {
                return true;
            }
        }





        // Connecting with a Turning Point
        for (int x = 0; x < gameUI.board.length; x++) {
            if (isDirectlyConnected(x1, y1, x, y1) && isDirectlyConnected(x, y1, x2, y2) && gameUI.board[x][y1] == null) {
                return true;
            }
            if (isDirectlyConnected(x1, y1, x, y2) && isDirectlyConnected(x, y2, x2, y2) && gameUI.board[x][y2] == null) {
                return true;
            }
        }

        for (int y = 0; y < gameUI.board[0].length; y++) {
            if (isDirectlyConnected(x1, y1, x1, y) && isDirectlyConnected(x1, y, x2, y2) && gameUI.board[x1][y] == null) {
                return true;
            }
            if (isDirectlyConnected(x1, y1, x2, y) && isDirectlyConnected(x2, y, x2, y2) && gameUI.board[x2][y] == null) {
                return true;
            }
        }

        // Use two turning points to connect
        for (int x3 = 0; x3 < gameUI.board.length; x3++) {
            for (int y3 = 0; y3 < gameUI.board[0].length; y3++) {
                if (gameUI.board[x3][y3] != null) continue; // Ignore non-empty positions

                for (int x4 = 0; x4 < gameUI.board.length; x4++) {
                    for (int y4 = 0; y4 < gameUI.board[0].length; y4++) {
                        if (gameUI.board[x4][y4] != null) continue; // Ignore non-empty positions

                        if (isDirectlyConnected(x1, y1, x3, y3) &&
                                isDirectlyConnected(x3, y3, x4, y4) &&
                                isDirectlyConnected(x4, y4, x2, y2)) {
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }


    private boolean isDirectlyConnected(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            for (int y = Math.min(y1, y2) + 1; y < Math.max(y1, y2); y++) {
                if (gameUI.board[x1][y] != null) {
                    return false;
                }
            }
            return true;
        }

        if (y1 == y2) {
            for (int x = Math.min(x1, x2) + 1; x < Math.max(x1, x2); x++) {
                if (gameUI.board[x][y1] != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }


}
