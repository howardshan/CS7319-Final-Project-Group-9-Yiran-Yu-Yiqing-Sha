package Selected.learn;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import Selected.homepage.HomePage;
import Selected.testlianliankan.Data;

public class Learn {
    private JFrame frame;
    private JButton[][] buttons;
    private Data data;
    private HomePage homePage;
    private final int HEIGHT = 5;
    private final int WIDTH = 4;
    private JButton returnButton;

    public Learn(Data data, HomePage homePage) {
        this.data = data;
        this.homePage = homePage;
        // Add frame
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
    // get the picture
    private static final String RESOURCES_BASE_PATH = "/resources/";
    // initialize the board.
    private JPanel initializeBoard() {
        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));
        buttons = new JButton[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                buttons[i][j] = new JButton();
                panel.add(buttons[i][j]);
                // 1,3 for picture, 2,4 for name
                if (j % 2 == 0) {
                    int num = (j / 2) * HEIGHT + i + 1;
                    URL imageURL = Learn.class.getResource(RESOURCES_BASE_PATH + num + ".png");
                    ImageIcon icon = new ImageIcon(imageURL);
                    buttons[i][j].setIcon(icon);
                } else {
                    int num = ((j - 1) / 2) * HEIGHT + i + 1;
                    String text = data.numberToText(num);
                    buttons[i][j].setText(text);
                    buttons[i][j].setIcon(null);
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
            Data dataInstance = new Data();
            HomePage homeInstance = new HomePage();  // A sample instance of HomePage. It may not be what you want.
            Learn learnInstance = new Learn(dataInstance, homeInstance);
            learnInstance.setVisible(true);
        });
    }
}
