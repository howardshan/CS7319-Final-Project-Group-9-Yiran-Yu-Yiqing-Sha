package Selected.homepage;
import Selected.testlianliankan.Data;
import Selected.testlianliankan.game;
import Selected.learn.Learn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HomePage {
    private JFrame frame;

    // add a difficultness
    private JComboBox<Integer> difficultyComboBox;
    private int diffLevel;
    public HomePage() {
        // Make Homepage
        frame = new JFrame("Find Country Flag");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new GridBagLayout());

        // Button to start the game
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        // Active learning page
        JButton learnButton = new JButton("Learn Country Flag");
        learnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLearn();
            }
        });
        //Set up Game Difficultness
        Integer[] difficultiness = {1,2,3};
        difficultyComboBox = new JComboBox<>(difficultiness);
        difficultyComboBox.setSelectedIndex(0);
        diffLevel = (Integer) difficultyComboBox.getSelectedItem();

        difficultyComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    diffLevel = (Integer) e.getItem();
                }
            }
        });
        // Set up a picker to adjust the difficulty level.
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 使用默认的FlowLayout，组件水平排列
        difficultyPanel.add(new JLabel("Select Difficulty:"));
        difficultyPanel.add(difficultyComboBox);
        // use GridBagConstraints for lay out
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        // Add to homepage
        frame.add(learnButton,gbc);
        frame.add(difficultyPanel, gbc);
        frame.add(startButton,gbc);


        // show at the middle
        frame.setLocationRelativeTo(null);
    }

    // show the hompage
    public void show() {
        frame.setVisible(true);
    }


    private void startGame() {
        frame.setVisible(false); // hide homepage
        game gameInstance = new game(this); // initial the game
        gameInstance.setVisible(true); // show the game page
    }

    private void startLearn() {
        frame.setVisible(false); // hide homepage
        Data dataInstance = new Data();
        Learn learnInstance = new Learn(dataInstance,this); //initial the learn page
        learnInstance.setVisible(true); // show the learn page
    }
    // active the homepage
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HomePage homePage = new HomePage();
                    homePage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public int getDiffLevel () {
        return diffLevel;
    }
}
