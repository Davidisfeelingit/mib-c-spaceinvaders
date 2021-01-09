package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Menu_GUI extends JPanel implements ActionListener {

    private Main_GUI main_gui;

    public Menu_GUI(Main_GUI main_GUI) throws FileNotFoundException {
        this.main_gui = main_GUI;

        setSize(800, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel titleLabel = new JLabel("SpAcE InVaDeRs");
        titleLabel.setFont(new Font("Chiller", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        JPanel buttonPanel = new JPanel(null);

        // Start button.
        JButton startButton = new JButton(new ImageIcon(("src/images/start.png")));
        startButton.addActionListener((e) -> startGame());
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(true);
        startButton.setBorderPainted(true);
        startButton.setBounds(310, 80, 180, 29);
        startButton.setPreferredSize(new Dimension(180, 29));
        startButton.setOpaque(false);
        buttonPanel.add(startButton);
        startButton.addActionListener(this);

        // Highscore button.
        JButton highscoreButton = new JButton(new ImageIcon("src/images/highscore.png"));
        highscoreButton.setOpaque(false);
        highscoreButton.setContentAreaFilled(true);
        highscoreButton.setBorderPainted(true);
        highscoreButton.setBounds(300, 135, 200, 26);
        highscoreButton.setPreferredSize(new Dimension(200, 26));
        highscoreButton.addActionListener(e -> {
            this.main_gui.openHighScoreGUI();
        });
        highscoreButton.addActionListener(this);
        buttonPanel.add(highscoreButton);

        // Exit button.
        JButton exitButton = new JButton(new ImageIcon(("src/images/exit.png")));
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(true);
        exitButton.setBorderPainted(true);
        exitButton.setBounds(340, 190, 120, 31);
        exitButton.setPreferredSize(new Dimension(120, 31));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel);
        setVisible(true);
    }

    public void startGame() {
      this.main_gui.openGameBoard();
        /*  EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders();
            ex.setVisible(true);
        });
        this.setVisible(false);*/
        //this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}