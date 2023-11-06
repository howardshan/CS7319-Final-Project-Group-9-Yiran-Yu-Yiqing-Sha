package Selected.testlianliankan;

import Selected.homepage.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class game extends JFrame {



    private SetUpGame.Tile[][] board;
    private JButton[][] buttons;

    public boolean comparasion = false;
    private Select select;
    private Compare compare;
    private Determin determin;
    private Update update;
    private Data data;
    private SetUpGame setUpGame;
    private HomePage homepage;
    private JButton returnButton;
    private JPanel gamePanel;
    public game(HomePage homepage) {
        this.homepage = homepage;
        int difflevel = homepage.getDiffLevel();
        int result;
        if (difflevel%2 == 1) {
            result = (int) Math.sqrt(Math.pow(2, difflevel)*8);}
        else { result = (int) Math.sqrt(Math.pow(2, difflevel)*16);}

        setUpGame = new SetUpGame(result, homepage);
        System.out.println(difflevel);


        this.board = setUpGame.getBoard(); // board comes from SetUpGame
        buttons = new JButton[setUpGame.getBoardSize()][setUpGame.getBoardSize()];

        setTitle("Find Country Flag");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the main layout to BorderLayout
        setLayout(new BorderLayout());

        // Initialize game panel and set its layout
        gamePanel = new JPanel(new GridLayout(setUpGame.getBoardSize(), setUpGame.getBoardSize()));

        // Add the gamePanel to the center of the JFrame
        add(gamePanel, BorderLayout.CENTER);
        // Initialize the game board buttons
        initButtons();






        // Other initializations
        this.data = new Data();
        this.compare = new Compare(this, board);
        this.select = new Select(setUpGame, compare, this, board, buttons, setUpGame.getBoardSize());
        this.determin = new Determin(homepage,select, compare, this, board, buttons, setUpGame.getBoardSize());
        this.update = new Update(this, data, buttons, setUpGame.getBoardSize(), board);

        // Add component listener to handle the resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                update.refreshUI();
            }
        });
        //returnButton
        returnButton = new JButton("Back");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToHomePage();
            }
        });

        // Add returnButton to the north (top) of the JFrame
        add(returnButton, BorderLayout.NORTH);
        // Initialize the game board buttons
        // Refresh UI
        update.refreshUI();
    }

    private void initButtons() {
        for (int i = 0; i < setUpGame.getBoardSize(); i++) {
            for (int j = 0; j < setUpGame.getBoardSize(); j++) {
                JButton button = new JButton();
                final int finalI = i;
                final int finalJ = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onButtonClicked(finalI, finalJ);
                    }
                });
                buttons[i][j] = button;
                gamePanel.add(button);
            }
        }
    }
    public void onButtonClicked(int x, int y) {
        //select tubes

        select.select(x,y);


        // get locations
        int SelectX = select.getSelectedX();
        int SelectY = select.getSelectedY();
        int Select2X = select.getSelected2X();
        int Select2Y = select.getSelected2Y();

        // if select 2 tubees
        if (Select2X != -1) {
            // run the comparasion
            comparasion = compare.canConnect(SelectX,SelectY,Select2X,Select2Y);
            // Determine the change
            determin.ReturnValue(x,y);
            System.out.println(Select2X+"yes");
            // Update the change
            update.refreshUI();
        }

    }

    public boolean getcomparasion() {
        return comparasion;
    }

    private static final String RESOURCES_BASE_PATH = "/resources/";








    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePageInstance = new HomePage();
            game gameInstance = new game(homePageInstance);
            gameInstance.setVisible(true);
        });
    }

    // return button


    private void returnToHomePage() {
        this.dispose();  // Close the game window
        homepage.show(); // Show the HomePage
    }




}
