package Selected.testlianliankan;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Update {
    private Data data;
    private game game;
    private JButton[][] buttons;
    private final int boardSize;
    private final SetUpGame.Tile[][] board;
    public Update (game game,Data data, JButton[][] buttons,int boardSize,SetUpGame.Tile[][] board ) {

        this.game = game;
        this.data = data;
        this.buttons = buttons;
        this.boardSize = boardSize;
        this.board = board;
    }

    private static final String RESOURCES_BASE_PATH = "/";

    public void refreshUI() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                SetUpGame.Tile tile = board[i][j];
                if (tile == null) {
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setText("");
                    continue;
                }
                if (tile.imageId == null && tile.number == null) {
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setEnabled(false);
                } else {
                    if (tile.imageId != null) { // show picture
                        URL resourceURL = game.class.getResource(RESOURCES_BASE_PATH + tile.imageId + ".png");
                        ImageIcon icon = new ImageIcon(resourceURL);
                        if (buttons[i][j].getWidth() > 0 && buttons[i][j].getHeight() > 0) {
                            Image img = icon.getImage();
                            Image resizedImg = img.getScaledInstance(buttons[i][j].getWidth(), buttons[i][j].getHeight(), Image.SCALE_SMOOTH);
                            buttons[i][j].setIcon(new ImageIcon(resizedImg));
                        } else {
                            buttons[i][j].setIcon(icon);
                        }
                       // System.out.println("Loading image: " + RESOURCES_BASE_PATH + tile.imageId + ".png");
                    } else if (tile.number != null) { // show number
                        String text = data.numberToText(tile.number);
                        buttons[i][j].setText(text);
                        buttons[i][j].setIcon(null); // clear icon
                       // System.out.println("Loading text: " + text);
                    }
                }
            }
        }    }
}
