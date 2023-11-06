package Unselected;

import Unselected.Subroutine.DataBase.User;
import Unselected.Subroutine.DataBase.UserDataAccess;
import Unselected.Subroutine.MainGame.GamePictures;
import Unselected.Subroutine.MainGame.GameUI;
import Unselected.Subroutine.Guidance.Guidance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridBagConstraints;

public class HomePage {
    private JFrame frame;

    // add a difficultness
    private JComboBox<Integer> difficultyComboBox;
    private int diffLevel;
    GridBagConstraints gbc = new GridBagConstraints();



    public HomePage() {
        frame = new JFrame("Find Country Flag");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new GridBagLayout());


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
        Integer[] difficultiness = {1,2,3,4,5};
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

        //username
        JButton enterNameButton = new JButton("Enter Name");
        enterNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                promptForName();
            }
        });

        //create a frame of username
        frame.add(enterNameButton, gbc);



        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        difficultyPanel.add(new JLabel("Select Difficulty:"));
        difficultyPanel.add(difficultyComboBox);
        // Using GridBagConstraints to control the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        frame.add(learnButton,gbc);
        frame.add(difficultyPanel, gbc);
        frame.add(startButton,gbc);



        frame.setLocationRelativeTo(null);
    }



    public void show() {
        frame.setVisible(true);
    }



    private void startGame() {
        frame.setVisible(false);
        GameUI gameInstance = new GameUI();

        gameInstance.setVisible(true);
    }

    //Guidance
    private void startLearn() {

        frame.setVisible(false);
        GamePictures picturesInstance = new GamePictures();
        Guidance learnInstance = new Guidance(picturesInstance,this);
        learnInstance.setVisible(true);
    }



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


    //username
    public void promptForName() {
        JDialog nameDialog = new JDialog(frame, "Enter Your Name", true);
        nameDialog.setSize(300, 200);
        nameDialog.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                if (!userName.matches("[a-zA-Z0-9_]+")) {
                    JOptionPane.showMessageDialog(nameDialog, "Username can only contain letters, numbers, and underscores.");
                    return;
                }

                UserDataAccess userDataAccess = new UserDataAccess();
                //userDataAccess.insertUser(userName);

                User existingUser = userDataAccess.getUserByUsername(userName);
                if (existingUser != null) {

                    JOptionPane.showMessageDialog(nameDialog, "该用户名已被使用，请选择其他用户名。");
                    return;
                }


                boolean isInserted = userDataAccess.insertUser(userName);
                if (isInserted) {
                    JOptionPane.showMessageDialog(nameDialog, "用户名已成功添加！");
                } else {
                    JOptionPane.showMessageDialog(nameDialog, "添加用户名失败，请重试。");
                }

                nameDialog.dispose();
                frame.setVisible(true);
            }
        });

        nameDialog.add(new JLabel("Please enter your name: "));
        nameDialog.add(nameField);
        nameDialog.add(submitButton);


        nameDialog.setLocationRelativeTo(frame);

        nameDialog.setVisible(true);
    }


}
