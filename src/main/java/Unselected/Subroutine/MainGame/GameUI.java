package Unselected.Subroutine.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameUI extends JFrame{
    GameLogic gameLogic;
    GamePictures gamePictures = new GamePictures();


    //Indicates each grid in the game
    public static class Tile {
        public final Integer imageId;//numbers of images
        public final Integer number;//names of images

        //A tile can only receive imageID/number at a time, not both.
        Tile(Integer imageId, Integer number) {
            if ((imageId == null && number == null) || (imageId != null && number != null)) {
                throw new IllegalArgumentException("Either ImageId or Number should be null, not both or neither");
            }
            this.imageId = imageId;
            this.number = number;
        }

    }


    public Tile[][] board;//Separate grid/positioning in the game
    public static JButton[][] buttons;//Buttons that interact with the user
    private final int boardSize = 10;


    public GameUI(){
        gameLogic = new GameLogic(this);
        setTitle("Find the Country Flag");
        setSize(1200, 800);//800，600
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(boardSize, boardSize));


        board = new Tile[boardSize][boardSize];
        buttons = new JButton[boardSize][boardSize];


        List<Tile> tiles = new ArrayList<>();

        int halfItems = (boardSize * boardSize) / 2;

        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < halfItems / 10; j++) {
                tiles.add(new Tile(i, null));//The object contains the number i, and there is no picture identifier (null means no picture)
                tiles.add(new Tile(null, i));//Set the picture identifier to a value and no numbers (null means no numbers)
            }
        }

        //The initialization part of the two-dimensional board array, which populates the board array with the elements of the tiles list (the Tile objects) in a certain order.
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = tiles.get(i * boardSize + j);//Assigns the tiles in the tiles list to their corresponding locations on the game board.
                //Inside get is the index, which is also equivalent to the order of numbers in the case.
            }
        }

        //Perform a random exchange, change order
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int x1 = random.nextInt(boardSize);
            int y1 = random.nextInt(boardSize);
            int x2 = random.nextInt(boardSize);
            int y2 = random.nextInt(boardSize);

            Tile temp = board[x1][y1];
            board[x1][y1] = board[x2][y2];
            board[x2][y2] = temp;
        }


        // Add buttons to JFrame/tile
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = new JButton();
                final int finalI = i;
                final int finalJ = j;

                button.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameLogic.onButtonClicked(finalI, finalJ);
                    }
                });
                buttons[i][j] = button;
                add(button);
            }
        }

        // Add component listener to handle the resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshUI();
            }
        });
        refreshUI();

    }



    private static final String RESOURCES_BASE_PATH = "/";

    //Iterate through the board array and update the corresponding buttons according to the state of each Tile object
    public void refreshUI() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Tile tile = board[i][j];
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
                    if (tile.imageId != null) { // Show picture
                        URL resourceURL = GameUI.class.getResource(RESOURCES_BASE_PATH + tile.imageId + ".png");
                        ImageIcon icon = new ImageIcon(resourceURL);


                        if (buttons[i][j].getWidth() > 0 && buttons[i][j].getHeight() > 0) {
                            Image img = icon.getImage();
                            Image resizedImg = img.getScaledInstance(buttons[i][j].getWidth(), buttons[i][j].getHeight(), Image.SCALE_SMOOTH);
                            buttons[i][j].setIcon(new ImageIcon(resizedImg));
                        } else {
                            buttons[i][j].setIcon(icon);
                        }
                        System.out.println("Loading image: " + RESOURCES_BASE_PATH + tile.imageId + ".png");
                    } else if (tile.number != null) { // Display the text corresponding to the number
                        String text = gamePictures.numberToText(tile.number);
                        buttons[i][j].setText(text);
                        buttons[i][j].setIcon(null); // Clear image icon
                        System.out.println("Loading text: " + text);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//Ensure that GUI creation and updates are executed on the event scheduling thread. Avoid potential thread safety issues.

            GameUI gameInstance = new GameUI();

            gameInstance.setVisible(true);


        });
    }

}
