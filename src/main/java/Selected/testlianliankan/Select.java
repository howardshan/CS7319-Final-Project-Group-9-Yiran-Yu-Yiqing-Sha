package Selected.testlianliankan;

import javax.swing.*;
import java.awt.*;

public class Select {
    public int selectedX = -1;
    public int selectedY = -1;
    public int selected2X = -1;
    public int selected2Y = -1;
    private JButton[][] buttons;
    private game game;



    private final SetUpGame.Tile[][] board;
    private final int boardSize;
    private Compare compare;
    private SetUpGame setUpGame;
    public Select(SetUpGame setUpGame,Compare compare, game game, SetUpGame.Tile[][] board, JButton[][] buttons, int boardSize) {
        this.setUpGame = setUpGame;
        this.game = game;
        this.board = board;
        this.buttons = buttons;
        this.boardSize = boardSize;
        this.compare = (compare != null) ? compare : new Compare(this.game, this.board);


    }

    public void select(int x, int y) {

        if (selectedX == -1) {
            selectedX = x;
            selectedY = y;
            buttons[selectedX][selectedY].setOpaque(true); // Set the button to be opaque
            buttons[selectedX][selectedY].setContentAreaFilled(true); // This will fill the content area
            buttons[selectedX][selectedY].setBackground(Color.BLUE);
        } else {
            selected2X = x;
            selected2Y = y;

        }

    }
    public int getSelectedX() {
        return  selectedX;
    }

    public int getSelectedY() {
        return  selectedY;
    }
    public int getSelected2X() {
        return  selected2X;
    }
    public int getSelected2Y() {
        return selected2Y;
    }
    public void resetSelection() {
        buttons[selectedX][selectedY].setBackground(null);
        buttons[selected2X][selected2Y].setBackground(null);
        selectedX = -1;
        selectedY = -1;
        selected2X = -1;
        selected2Y = -1;
    }
}
