package Unselected.Subroutine.Guidance;

import Unselected.HomePage;
import Unselected.Subroutine.MainGame.GamePictures;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Guidance {
    private JFrame frame;
    private JButton[][] buttons;
    private GamePictures pictures;
    private HomePage homePage;
    private final int HEIGHT = 5;//3
    private final int WIDTH = 4;//4
    private JButton returnButton;



    public Guidance(GamePictures pictures, HomePage homePage) {
        this.pictures = pictures;
        this.homePage = homePage;

        frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel learnPanel = initializeBoard();
        frame.add(learnPanel, BorderLayout.CENTER);

        returnButton = new JButton("Back");
        returnButton.addActionListener(e -> returnToHomePage());
        frame.add(returnButton, BorderLayout.NORTH);
    }

    private static final String RESOURCES_BASE_PATH = "/";

    private JPanel initializeBoard() {
        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));
        buttons = new JButton[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                buttons[i][j] = new JButton();
                panel.add(buttons[i][j]);

                if (j % 2 == 0) {
                    int num = (j / 2) * HEIGHT + i + 1;
                    if(num <= 10){
                        URL imageURL = Guidance.class.getResource(RESOURCES_BASE_PATH + num + ".png");

                    ImageIcon icon = new ImageIcon(imageURL);
                    buttons[i][j].setIcon(icon);
                    }
                } else {
                    int num = ((j - 1) / 2) * HEIGHT + i + 1;
                    if(num <= 10){
                        String text = pictures.numberToText(num);
                        buttons[i][j].setText(text);
                        buttons[i][j].setIcon(null);
                    }

                }
            }
        }
        return panel;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private void returnToHomePage() {
        frame.dispose();
        homePage.show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GamePictures picturesInstance = new GamePictures();
            HomePage homeInstance = new HomePage();  // A sample instance of HomePage. It may not be what you want.
            Guidance learnInstance = new Guidance(picturesInstance, homeInstance);
            learnInstance.setVisible(true);
        });
    }
}
