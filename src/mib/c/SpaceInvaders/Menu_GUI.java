package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu_GUI extends JPanel implements ActionListener {

    private Main_GUI main_gui;

    public Menu_GUI(Main_GUI main_GUI) {
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
        startButton.addActionListener((e) -> this.main_gui.openGameBoard());
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
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?",
                        "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    main_GUI.dispose();
                }
        });

        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel);
        setVisible(true);

    }

    private void setDefaultCloseOperation(int doNothingOnClose) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}