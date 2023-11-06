package Selected.testlianliankan;


import javax.swing.*;

import Selected.homepage.HomePage;

public class Determin {

    private Select select;
    private JButton[][] buttons;
    private game game;
    private HomePage homePage;


    private final SetUpGame.Tile[][] board;
    private final int boardSize;
    private Compare compare;
    private int selectedX = -1;
    private int selectedY = -1;
    private int selected2X = -1;
    private int selected2Y = -1;
    public Determin(HomePage homePage,Select select, Compare compare, game game, SetUpGame.Tile[][] board, JButton[][] buttons, int boardSize) {
        this.game = game;
        this.board = board;
        this.buttons = buttons;
        this.boardSize = boardSize;
        this.compare = (compare != null) ? compare : new Compare(this.game, this.board);
        this.select = select;
        this.homePage = homePage;

        if (select != null) {
            this.selectedX = select.getSelectedX();
            this.selectedY = select.getSelectedY();
            this.selected2X = select.getSelected2X();
            this.selected2Y = select.getSelected2Y();
        }
    }



    public void ReturnValue(int x, int y) {
        selectedX = select.getSelectedX();
        selectedY = select.getSelectedY();
        selected2X = select.getSelected2X();
        selected2Y = select.getSelected2Y();






        // Make sure the selected indexes are within the bounds of the array
        if(selectedX >= 0 && selectedY >= 0 && selected2X >= 0 && selected2Y >= 0) {
            // Check if the selected tile is not the same as the previously selected tile
            if (selectedX != selected2X || selectedY != selected2Y) {
                if (game.getcomparasion()) {
                    System.out.println("Apple");
                    board[selectedX][selectedY] = null;
                    board[selected2X][selected2Y] = null;
                    buttons[selectedX][selectedY].setBackground(null);
                    buttons[selected2X][selected2Y].setBackground(null);
                    // if all have been removed, show you win


                }
            }
            // Reset the selection for the next turn
            select.resetSelection();

        }
        // Reset the selection if the indexes are not valid
        else {


            select.resetSelection();

        }
        if (isBoardEmpty()) {
            showWinDialog();
            returnToHomePage();
        }
    }
    private boolean isBoardEmpty() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinDialog() {
        JOptionPane.showMessageDialog(game, "You win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }
    private void returnToHomePage() {
        game.dispose();  // Close the game window
        homePage.show(); // Show the HomePage
    }
}
