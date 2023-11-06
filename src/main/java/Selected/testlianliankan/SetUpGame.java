package Selected.testlianliankan;

import javax.swing.*;
import java.util.*;

import Selected.homepage.HomePage;

public class SetUpGame {

    public static class Tile {
        final Integer imageId;
        final Integer number;

        Tile(Integer imageId, Integer number) {
            if ((imageId == null && number == null) || (imageId != null && number != null)) {
                throw new IllegalArgumentException("Either ImageId or Number should be null, not both or neither");
            }
            this.imageId = imageId;
            this.number = number;
        }

    }
    private Tile[][] board;
    private JButton[][] buttons;
    private final int boardSize;
    private HomePage homePage;
    private int diffLevel = 2;



    public SetUpGame(int boardSize,HomePage homePage) {
        this.boardSize = boardSize;
        this.homePage =homePage;
        diffLevel = homePage.getDiffLevel();
        board = new Tile[boardSize][boardSize];
        buttons = new JButton[boardSize][boardSize];
        initializeBoard();
        shuffleBoard();
    }

    private void initializeBoard() {

        List<Tile> tiles = new ArrayList<>();
        int halfItems = (boardSize * boardSize) / 2;
        int numOfFlag =(int) Math.pow(2,diffLevel);
        //Shuffle the flags
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List <Integer> flag = numbers.subList(0,numOfFlag);
        System.out.println("setup"+flag);
        for (int i: flag) {
            for (int j = 0; j < halfItems / numOfFlag; j++) {
                tiles.add(new Tile(i, null));
                tiles.add(new Tile(null, i));
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = tiles.get(i * boardSize + j);
            }
        }
    }

    private void shuffleBoard() {
        // random shuffle
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
    }

    public Tile[][] getBoard() {
        return board;
    }

    public JButton[][] getButtons() {
        return buttons;
    }
    public int getBoardSize() {
        return boardSize;
    }

}
